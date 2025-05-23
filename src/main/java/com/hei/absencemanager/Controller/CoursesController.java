package com.hei.absencemanager.Controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hei.absencemanager.Entity.Course;
import com.hei.absencemanager.Service.CoursesServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CoursesController {
    private CoursesServices coursesServices;

    public CoursesController(CoursesServices coursesServices) {
        this.coursesServices = coursesServices;
    }

    @GetMapping("courses")
    public List<Course> readCoursesList() throws SQLException {
        return coursesServices.readCoursesList();
    }

    @PostMapping("course")
    public Course addCourse(@RequestBody Course toAdd) throws SQLException {
        return coursesServices.addCourse(toAdd);
    }

    @GetMapping("course/{id}")
    public Course searchOneCourse(@PathVariable int id) throws SQLException {
        return coursesServices.searchOneCourse(id);
    }

    @PutMapping("course/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course toUpdate) throws SQLException {
        return coursesServices.updateCourse(id, toUpdate);
    }

    @DeleteMapping("course/{id}")
    public void deleteById(@PathVariable int id) throws SQLException {
        coursesServices.deleteById(id);
    }
}
