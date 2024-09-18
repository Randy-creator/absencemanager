package com.hei.absencemanager.Controller;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Service.studentServices;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping("/student")
    public Student postMethodName(@RequestBody Student student) throws SQLException {
        return studentService.createStudent(student);
    }

    @GetMapping("/students/name/{toSearch}")
    public List<Student> getMethodName(@PathVariable String toSearch) throws SQLException {
        return studentService.findStudents(toSearch);
    }

    @PutMapping("student/{std}")
    public Student putMethodName(@PathVariable String std, @RequestBody Student toUpdate) throws SQLException {
        return studentService.updateStudent(std, toUpdate);
    }

}
