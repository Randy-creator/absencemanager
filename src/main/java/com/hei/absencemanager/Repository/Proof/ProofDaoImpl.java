package com.hei.absencemanager.Repository.Proof;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hei.absencemanager.Entity.Proof;
import com.hei.absencemanager.Repository.DatabaseConnection;

@Repository
public class ProofDaoImpl implements ProofDao {
    private DatabaseConnection db;

    public ProofDaoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public List<Proof> readProofList() throws SQLException {
        List<Proof> proofs = new ArrayList<>();
        Connection connection = db.connect();

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT " +
                    "Proof.proofId, " +
                    "Student.STD AS std, " +
                    "Course.courseName, " +
                    "Proof.date, " +
                    "Proof.reason " +
                    "FROM Proof " +
                    "JOIN isAbsent ON Proof.attendanceId = isAbsent.attendanceId " +
                    "JOIN Student ON isAbsent.stdRef = Student.STD " +
                    "JOIN Course ON isAbsent.courseId = Course.courseId;";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Proof toAdd = new Proof(
                        rs.getInt("proofId"),
                        rs.getString("std"),
                        rs.getString("courseName"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getString("reason"));
                proofs.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return proofs;
    }

    @Override
    public void deleteProofByDetails(String std, String courseName, LocalDateTime date) throws SQLException {
        String sql = "DELETE FROM Proof " +
                "USING isAbsent, Course, Student " +
                "WHERE Proof.attendanceId = isAbsent.attendanceId " +
                "AND isAbsent.stdRef = Student.STD " +
                "AND isAbsent.courseId = Course.courseId " +
                "AND Student.STD = ? " +
                "AND Course.courseName = ? " +
                "AND Proof.date = ?";

        try (Connection connection = db.connect();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, std);
            stmt.setString(2, courseName);
            stmt.setObject(3, date);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No matching proof found to delete.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
