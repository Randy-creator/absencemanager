package com.hei.absencemanager.Repository.Courses;

import java.sql.SQLException;
import java.util.List;

import com.hei.absencemanager.Entity.Course;

public interface CoursesDao {
    public List<Course> readCoursesList() throws SQLException;

    public Course addCourse(Course toAdd) throws SQLException; 
}
