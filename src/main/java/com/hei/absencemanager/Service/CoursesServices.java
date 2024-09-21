package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.Course;
import com.hei.absencemanager.Repository.Courses.CoursesDaoImpl;

@Service
public class CoursesServices {
    private CoursesDaoImpl coursesRepo;

    public CoursesServices(CoursesDaoImpl coursesRepo) {
        this.coursesRepo = coursesRepo;
    }

    public List<Course> readCoursesList() throws SQLException {
        return coursesRepo.readCoursesList();
    }

    public Course addCourse(Course toAdd) throws SQLException {
        return coursesRepo.addCourse(toAdd);
    }
}
