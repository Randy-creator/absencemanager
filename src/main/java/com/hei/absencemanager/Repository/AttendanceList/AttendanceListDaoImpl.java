package com.hei.absencemanager.Repository.AttendanceList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hei.absencemanager.Entity.AbsenceRequest;
import com.hei.absencemanager.Entity.Attend;
import com.hei.absencemanager.Repository.DatabaseConnection;

@Repository
public class AttendanceListDaoImpl implements AttendanceListDao {
    private DatabaseConnection db;

    public AttendanceListDaoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public List<Attend> readAttendanceList() throws SQLException {
        List<Attend> attendanceList = new ArrayList<>();
        Connection connection = db.connect();

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT\n" +
                    "    s.STD,\n" +
                    "    s.firstName,\n" +
                    "    s.lastName,\n" +
                    "    c.courseName,\n" +
                    "    a.date,\n" +
                    "    a.presenceStatus\n" +
                    "FROM\n" +
                    "    Attend a\n" +
                    "    JOIN Student s ON a.stdRef = s.STD\n" +
                    "    JOIN Course c ON a.courseId = c.courseId;";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String std = rs.getString("STD");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String courseName = rs.getString("courseName");

                LocalDateTime date = rs.getDate("date").toLocalDate().atStartOfDay();

                char presenceStatus = rs.getString("presenceStatus").charAt(0);

                Attend toAdd = new Attend(std, firstName, lastName, courseName, date, presenceStatus);

                attendanceList.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return attendanceList;
    }

    private int getCourseId(String courseName) throws SQLException {
        String sql = "SELECT courseId FROM Course WHERE courseName = ?";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("courseId");
            } else {
                throw new SQLException("Course not found: " + courseName);
            }
        }
    }

    @Override
    public List<Attend> getStudentsByCourseAndDate(String courseName, LocalDateTime date) throws SQLException {
        List<Attend> attendanceList = new ArrayList<>();
        Connection connection = db.connect();

        String sql = "SELECT s.STD, s.firstName, s.lastName, c.courseName, a.date, a.presenceStatus " +
                "FROM Attend a " +
                "JOIN Student s ON a.stdRef = s.STD " +
                "JOIN Course c ON a.courseId = c.courseId " +
                "WHERE c.courseName = ? AND DATE(a.date) = DATE(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, courseName);
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String std = rs.getString("STD");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String course = rs.getString("courseName");
                LocalDateTime attendanceDate = rs.getTimestamp("date").toLocalDateTime();
                char presenceStatus = rs.getString("presenceStatus").charAt(0);

                Attend toAdd = new Attend(std, firstName, lastName, course, attendanceDate, presenceStatus);
                attendanceList.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return attendanceList;
    }

    @Override
    public List<Attend> getAttendanceByStudent(String stdRef) throws SQLException {
        List<Attend> attendanceList = new ArrayList<>();
        String sql = "SELECT s.STD, s.firstName, s.lastName, c.courseName, a.date, a.presenceStatus " +
                "FROM Attend a " +
                "JOIN Student s ON a.stdRef = s.STD " +
                "JOIN Course c ON a.courseId = c.courseId " +
                "WHERE s.STD = ?";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stdRef);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String std = rs.getString("STD");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String courseName = rs.getString("courseName");
                LocalDateTime attendanceDate = rs.getTimestamp("date").toLocalDateTime();
                char presenceStatus = rs.getString("presenceStatus").charAt(0);

                Attend toAdd = new Attend(std, firstName, lastName, courseName, attendanceDate, presenceStatus);
                attendanceList.add(toAdd);
            }
        }
        return attendanceList;
    }

    @Override
    public void deleteAttendance(String stdRef, String courseName, LocalDateTime date) throws SQLException {
        String sql = "DELETE FROM Attend WHERE stdRef = ? AND courseId = ? AND DATE(date) = DATE(?)";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stdRef);
            ps.setInt(2, getCourseId(courseName));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(date));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No attendance record found for student " + stdRef + " in course " + courseName
                        + " on " + date);
            }
        }
    }

    @Override
    public void updateAttendance(String stdRef, String courseName, LocalDateTime date, char newStatus)
            throws SQLException {
        String sql = "UPDATE Attend SET presenceStatus = ? WHERE stdRef = ? AND courseId = ? AND DATE(date) = DATE(?)";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(newStatus));
            ps.setString(2, stdRef);
            ps.setInt(3, getCourseId(courseName));
            ps.setTimestamp(4, java.sql.Timestamp.valueOf(date));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No attendance record found for student " + stdRef + " in course " + courseName
                        + " on " + date);
            }
        }
    }

    @Override
    public List<Attend> getAttendanceByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<Attend> attendanceList = new ArrayList<>();
        String sql = "SELECT s.STD, s.firstName, s.lastName, c.courseName, a.date, a.presenceStatus " +
                "FROM Attend a " +
                "JOIN Student s ON a.stdRef = s.STD " +
                "JOIN Course c ON a.courseId = c.courseId " +
                "WHERE a.date BETWEEN ? AND ?";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(startDate));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(endDate));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String std = rs.getString("STD");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String courseName = rs.getString("courseName");
                LocalDateTime attendanceDate = rs.getTimestamp("date").toLocalDateTime();
                char presenceStatus = rs.getString("presenceStatus").charAt(0);

                Attend toAdd = new Attend(std, firstName, lastName, courseName, attendanceDate, presenceStatus);
                attendanceList.add(toAdd);
            }
        }
        return attendanceList;
    }

    @Override
    public List<Attend> getAllAttendanceByCourse(String courseName) throws SQLException {
        List<Attend> attendanceList = new ArrayList<>();
        String sql = "SELECT s.STD, s.firstName, s.lastName, c.courseName, a.date, a.presenceStatus " +
                "FROM Attend a " +
                "JOIN Student s ON a.stdRef = s.STD " +
                "JOIN Course c ON a.courseId = c.courseId " +
                "WHERE c.courseName = ?";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String std = rs.getString("STD");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                LocalDateTime attendanceDate = rs.getTimestamp("date").toLocalDateTime();
                char presenceStatus = rs.getString("presenceStatus").charAt(0);

                Attend toAdd = new Attend(std, firstName, lastName, courseName, attendanceDate, presenceStatus);
                attendanceList.add(toAdd);
            }
        }
        return attendanceList;
    }

    @Override
    public Attend createAttendance(Attend attend) throws SQLException {
        String sql = "INSERT INTO Attend (stdRef, courseId, date, presenceStatus) VALUES (?, ?, ?, ?)";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, attend.getStd());
            ps.setInt(2, getCourseId(attend.getCourseName()));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(attend.getDate()));
            ps.setString(4, String.valueOf(attend.getPresenceStatus()));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to insert attendance record for student " + attend.getStd());
            }
        }
        return attend;
    }

    @Override
    public void presentUpdate(AbsenceRequest listOfAbsent, LocalDateTime date, String courseName) throws SQLException {
        String sql = "UPDATE Attend " +
                "SET presenceStatus = 'p' " +
                "WHERE stdRef NOT IN (?) AND courseId = ? AND DATE(date) = DATE(?)";

        try (Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {

            String absentStudentsStr = String.join(",", listOfAbsent.getAbsentStudentsStd());

            ps.setString(1, absentStudentsStr);
            ps.setInt(2, getCourseId(courseName));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(date));

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No attendance records were updated for course " + courseName + " on " + date);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
