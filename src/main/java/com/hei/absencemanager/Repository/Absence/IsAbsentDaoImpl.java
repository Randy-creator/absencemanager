package com.hei.absencemanager.Repository.Absence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hei.absencemanager.Entity.IsAbsent;
import com.hei.absencemanager.Repository.DatabaseConnection;

@Repository
public class IsAbsentDaoImpl implements IsAbsentDao {
    private final DatabaseConnection db;

    public IsAbsentDaoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public List<IsAbsent> readAbsenceList() throws SQLException {
        List<IsAbsent> isAbsentList = new ArrayList<>();
        Connection connection = db.connect();

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT " +
                    "s.STD AS StudentID, " +
                    "c.courseName AS CourseName, " +
                    "ia.isJustified AS IsJustified, " +
                    "ia.date AS AbsenceDate, " +
                    "p.reason AS ProofReason, " +
                    "p.date AS ProofDate, " +
                    "s.CORstatus AS CORStatus " +
                    "FROM isAbsent ia " +
                    "JOIN Student s ON ia.stdRef = s.STD " +
                    "JOIN Course c ON ia.courseId = c.courseId " +
                    "LEFT JOIN Proof p ON ia.attendanceId = p.attendanceId;";

            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                IsAbsent toAdd = new IsAbsent(
                        rs.getString("StudentID"),
                        rs.getBoolean("IsJustified"),
                        rs.getTimestamp("AbsenceDate").toLocalDateTime(),
                        rs.getString("ProofReason"),
                        rs.getTimestamp("ProofDate") != null ? rs.getTimestamp("ProofDate").toLocalDateTime() : null,
                        rs.getBoolean("CORStatus"),
                        rs.getString("CourseName"));
                isAbsentList.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return isAbsentList;
    }

    @Override
    public List<IsAbsent> readOneStudentsAbsences(String std) throws SQLException {
        List<IsAbsent> isAbsentList = new ArrayList<>();
        Connection connection = db.connect();

        String sql = "SELECT " +
                "s.STD AS StudentID, " +
                "c.courseName AS CourseName, " +
                "ia.isJustified AS IsJustified, " +
                "ia.date AS AbsenceDate, " +
                "p.reason AS ProofReason, " +
                "p.date AS ProofDate, " +
                "s.CORstatus AS CORStatus " +
                "FROM isAbsent ia " +
                "JOIN Student s ON ia.stdRef = s.STD " +
                "JOIN Course c ON ia.courseId = c.courseId " +
                "LEFT JOIN Proof p ON ia.attendanceId = p.attendanceId " +
                "WHERE s.STD = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, std);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                IsAbsent toAdd = new IsAbsent(
                        rs.getString("StudentID"),
                        rs.getBoolean("IsJustified"),
                        rs.getTimestamp("AbsenceDate").toLocalDateTime(),
                        rs.getString("ProofReason"),
                        rs.getTimestamp("ProofDate") != null ? rs.getTimestamp("ProofDate").toLocalDateTime() : null,
                        rs.getBoolean("CORStatus"),
                        rs.getString("CourseName"));
                isAbsentList.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            connection.close();
        }
        return isAbsentList;
    }

    @Override
    public void updateAbsence(String std, String courseName, LocalDateTime date, IsAbsent toUpdate)
            throws SQLException {
        String sql = "UPDATE isAbsent ia " +
                "JOIN Student s ON ia.stdRef = s.STD " +
                "JOIN Course c ON ia.courseId = c.courseId " +
                "SET ia.isJustified = ?, " +
                "    ia.attendanceId = ?, " +
                "    ia.date = ?, " +
                "    s.CORstatus = ? " +
                "WHERE s.STD = ? AND c.courseName = ? AND ia.date = ?";

        Connection connection = db.connect();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setBoolean(1, toUpdate.isJustified());
            pstmt.setString(2, toUpdate.getProofReason());
            pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(toUpdate.getAbsenceDate()));
            pstmt.setBoolean(4, toUpdate.isCorStatus());
            pstmt.setString(5, std);
            pstmt.setString(6, courseName);
            pstmt.setTimestamp(7, java.sql.Timestamp.valueOf(date));

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No records updated. Student or absence not found.");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        } finally {
            connection.close();
        }
    }

}
