package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.Teacher;
import com.hei.absencemanager.Repository.Teacher.TeacherDaoImpl;

@Service
public class TeacherServices {
    private TeacherDaoImpl teacherRepo;

    public TeacherServices(TeacherDaoImpl teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> readTeachersList() throws SQLException {
        return teacherRepo.readTeachersList();
    }

    public Teacher addNewTeacher(Teacher teacher) throws SQLException {
        return teacherRepo.addNewTeacher(teacher);
    }

    public Teacher searchOneTeacher(int id) throws SQLException {
        return teacherRepo.searchOneTeacher(id);
    }

    public Teacher updateTeacherInfo(int id, Teacher toUpdate) throws SQLException {
        return teacherRepo.updateTeacherInfo(id, toUpdate);
    }

    public void deleteById(int id) throws SQLException {
        teacherRepo.deleteById(id);
    }
}
