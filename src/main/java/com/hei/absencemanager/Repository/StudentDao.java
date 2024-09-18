package com.hei.absencemanager.Repository;

import com.hei.absencemanager.Entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    public List<Student> readStudentList() throws SQLException;

    public Student searchOneStudent(String std) throws SQLException;

    public Student createStudent(Student student) throws SQLException;

    public List<Student> searchStudentName(String toSearch) throws SQLException;

    public Student updateStudentInfo(String std, Student toUpdate) throws SQLException;
}