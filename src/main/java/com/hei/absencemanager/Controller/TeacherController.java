package com.hei.absencemanager.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.hei.absencemanager.Entity.Teacher;
import com.hei.absencemanager.Service.TeacherServices;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class TeacherController {
    private TeacherServices teacherService;

    public TeacherController(TeacherServices teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("teachers")
    public List<Teacher> readTeachersList() throws SQLException {
        return teacherService.readTeachersList();
    }

    @PostMapping("teacher")
    public Teacher addTeacher(@RequestBody Teacher teacher) throws SQLException {
        return teacherService.addNewTeacher(teacher);
    }

    @GetMapping("teacher/{id}")
    public Teacher searchOneTeacher(@PathVariable int id) throws SQLException {
        return teacherService.searchOneTeacher(id);
    }

    @PutMapping("teacher/{id}")
    public Teacher updateTeacherInfo(@PathVariable int id, @RequestBody Teacher toUpdate) throws SQLException {
        return teacherService.updateTeacherInfo(id, toUpdate);
    }

    @DeleteMapping("teacher/{id}")
    public void deleteById(@PathVariable int id) throws SQLException {
        teacherService.deleteById(id);
    }
}
