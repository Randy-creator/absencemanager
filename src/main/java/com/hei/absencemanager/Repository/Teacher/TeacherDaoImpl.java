package com.hei.absencemanager.Repository.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Entity.Teacher;
import com.hei.absencemanager.Repository.DatabaseConnection;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    private DatabaseConnection db;

    public TeacherDaoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public List<Teacher> readTeachersList() throws SQLException {
        List<Teacher> teacher = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT * FROM Teacher";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Teacher toAdd = new Teacher(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("teacherRef"));
                teacher.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return teacher;
    }

    @Override
    public Teacher addNewTeacher(Teacher toAdd) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        String sql = "INSERT INTO Student (firstName, lastName, teacherRef) VALUES ('"
                + toAdd.getFirstName() + "', '"
                + toAdd.getLastName() + "', '"
                + toAdd.getTeacherRef() + ")";

        try (Statement stm = connection.createStatement()) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return toAdd;
    }

    @Override
    public Teacher searchOneTeacher(int id) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        Teacher toSearch = null;

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT * FROM Teacher WHERE std ='" + id + "'";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                toSearch = new Teacher(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("teacherRef"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return toSearch;
    }

    @Override
    public Teacher updateTeacherInfo(int id, Teacher toUpdate) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        try (Connection connection = db.connect()) {
            String sql = "UPDATE Teacher SET teacherRef= ?, email = ?, firstName = ?, lastName = ? WHERE teacherRef = ?";

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setInt(1, toUpdate.getTeacherRef());
                stm.setString(2, toUpdate.getFirstName());
                stm.setString(3, toUpdate.getLastName());
                stm.setFloat(4, id);

                int rowsUpdated = stm.executeUpdate();
                if (rowsUpdated == 0) {
                    throw new SQLException("No student found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return toUpdate;
    }

    @Override
    public void deleteById(int id) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        try (Connection connection = db.connect()) {
            String sql = "DELETE FROM Teacher WHERE teacherRef = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted == 0) {
                    throw new SQLException("No student found with STD: " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    };
}