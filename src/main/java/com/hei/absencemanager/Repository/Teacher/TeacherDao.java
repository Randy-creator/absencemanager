package com.hei.absencemanager.Repository.Teacher;

import java.sql.SQLException;
import java.util.List;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Entity.Teacher;

public interface TeacherDao {
    public List<Teacher> readTeachersList() throws SQLException;

    public Teacher addNewTeacher(Teacher toAdd) throws SQLException;

    public Teacher searchOneTeacher(int id) throws SQLException;

    public Teacher updateTeacherInfo(int id, Teacher toUpdate) throws SQLException;

    public void deleteById(int id) throws SQLException;

}
