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

}
