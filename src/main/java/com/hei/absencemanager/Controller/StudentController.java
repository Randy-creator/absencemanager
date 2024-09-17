package com.hei.absencemanager.Controller;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Service.absenceManagerService;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StudentController {
    private absenceManagerService studentService;

    public StudentController(absenceManagerService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() throws SQLException {
        return studentService.getStudentsList();
    }
}
