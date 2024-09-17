package com.hei.absencemanager.Controller;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Service.studentServices;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class StudentController {
    private studentServices studentService;

    public StudentController(studentServices studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() throws SQLException {
        return studentService.getStudentsList();
    }

    @GetMapping("/students/{std}")
    public Student getAstudent(@PathVariable String std) throws SQLException {
        return studentService.getOneStudent(std);
    }
}
