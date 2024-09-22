package com.hei.absencemanager.Repository.Courses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hei.absencemanager.Entity.Course;
import com.hei.absencemanager.Entity.Teacher;
import com.hei.absencemanager.Repository.DatabaseConnection;

@Repository
public class CoursesDaoImpl implements CoursesDao {
    private DatabaseConnection db;

    public CoursesDaoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public List<Course> readCoursesList() throws SQLException {
        List<Course> courses = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT * FROM Course";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Course toAdd = new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"));
                courses.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return courses;
    }

    @Override
    public Course addCourse(Course toAdd) throws SQLException {
        String sql = "INSERT INTO Course (courseid, coursename) VALUES (?, ?)";

        try (Connection connection = db.connect();
                PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, toAdd.getCourseId());
            stm.setString(2, toAdd.getCourseName());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return toAdd;
    }

    @Override
    public Course updateCourse(int id, Course toUpdate) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        try (Connection connection = db.connect()) {
            String sql = "UPDATE Course SET courseName = ?, courseId = ? WHERE courseId = ?";

            try (PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, toUpdate.getCourseName());
                stm.setInt(2, toUpdate.getCourseId());
                stm.setFloat(3, id);

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
            String sql = "DELETE FROM Course WHERE courseId = ?";

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
    }

    @Override
    public Course searchOneCourse(int id) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.connect();

        Course toSearch = null;

        try (Statement stm = connection.createStatement()) {
            String sql = "SELECT * FROM Course WHERE courseId ='" + id + "'";
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                toSearch = new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return toSearch;
    }

}
