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

    public void checkAndUpdateIsJustified() throws SQLException {
        Connection connection = db.connect();

        String selectSql = "SELECT ia.attendanceId, p.reason, ia.isJustified " +
                "FROM isAbsent ia " +
                "LEFT JOIN Proof p ON ia.attendanceId = p.attendanceId " +
                "WHERE ia.isJustified = true AND p.reason IS NULL";

        String updateSql = "UPDATE isAbsent SET isJustified = false WHERE attendanceId = ?";

        try (PreparedStatement selectPstmt = connection.prepareStatement(selectSql);
                PreparedStatement updatePstmt = connection.prepareStatement(updateSql)) {

            ResultSet rs = selectPstmt.executeQuery();

            while (rs.next()) {
                int attendanceId = rs.getInt("attendanceId");
                boolean isJustified = rs.getBoolean("isJustified");
                String proofReason = rs.getString("reason");

                if (proofReason == null && isJustified) {
                    updatePstmt.setInt(1, attendanceId);
                    updatePstmt.executeUpdate();
                    System.out.println("Updated isJustified to false for attendanceId: " + attendanceId);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<IsAbsent> readAbsenceList() throws SQLException {
        checkAndUpdateIsJustified();
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
        checkAndUpdateIsJustified();
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
        }
        return isAbsentList;
    }

    @Override
    public void updateCORStatusForUnjustifiedAbsences() throws SQLException {
        Connection connection = db.connect();

        String countSql = "SELECT stdRef, COUNT(*) AS unjustifiedCount " +
                "FROM isAbsent " +
                "WHERE isJustified = false " +
                "GROUP BY stdRef " +
                "HAVING COUNT(*) >= 3";

        String updateSql = "UPDATE Student SET CORstatus = true WHERE STD = ?";

        try (PreparedStatement countPstmt = connection.prepareStatement(countSql);
                PreparedStatement updatePstmt = connection.prepareStatement(updateSql)) {

            ResultSet rs = countPstmt.executeQuery();

            while (rs.next()) {
                String stdRef = rs.getString("stdRef");
                int unjustifiedCount = rs.getInt("unjustifiedCount");

                updatePstmt.setString(1, stdRef);
                updatePstmt.executeUpdate();
                System.out.println("Updated CORStatus to true for student: " + stdRef + " (Unjustified count: "
                        + unjustifiedCount + ")");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
