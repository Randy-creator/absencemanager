package com.hei.absencemanager.Repository;

import com.hei.absencemanager.Entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    public List<Student> readStudentList() throws SQLException;
}
