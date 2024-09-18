package com.hei.absencemanager.Repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hei.absencemanager.Entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {
    private DatabaseConnection db;

    public StudentDaoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public List<Student> readStudentList() throws SQLException {
        List<Student> student = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT * FROM Student";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Student toAdd = new Student(
                        rs.getString("std"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("group"),
                        rs.getBoolean("corstatus"));
                student.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return student;
    }

    @Override
    public Student searchOneStudent(String std) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        Student toAdd = null;

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT * FROM Student WHERE std ='" + std + "'";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                toAdd = new Student(
                        rs.getString("std"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("group"),
                        rs.getBoolean("corstatus"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return toAdd;
    }

    @Override
    public Student createStudent(Student student) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        String sql = "INSERT INTO Student (std, email, firstName, lastName, \"group\", corstatus) VALUES ('"
                + student.getStd() + "', '"
                + student.getEmail() + "', '"
                + student.getFirstName() + "', '"
                + student.getLastName() + "', '"
                + student.getGroup() + "', "
                + student.getCORstatus() + ")";

        try (Statement stm = connection.createStatement()) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return student;
    }

    @Override
    public List<Student> searchStudentName(String toSearch) throws SQLException {
        List<Student> student = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT * FROM Student WHERE firstName ILIKE '%" + toSearch + "%'";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Student toAdd = new Student(
                        rs.getString("std"),
                        rs.getString("email"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("group"),
                        rs.getBoolean("corstatus"));
                student.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return student;
    }

    @Override
    public Student updateStudentInfo(String std, Student toUpdate) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        String sql = "UPDATE Student SET " +
                "email = '" + toUpdate.getEmail() + "', " +
                "firstName = '" + toUpdate.getFirstName() + "', " +
                "lastName = '" + toUpdate.getLastName() + "', " +
                "\"group\" = '" + toUpdate.getGroup() + "', " +
                "corstatus = " + toUpdate.getCORstatus() +
                " WHERE std = '" + std + "'";

        try (Statement stm = connection.createStatement()) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return toUpdate;
    }

}
