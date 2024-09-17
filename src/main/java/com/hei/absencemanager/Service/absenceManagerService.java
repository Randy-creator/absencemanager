package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Repository.StudentDaoImpl;

@Service
public class absenceManagerService {
    private StudentDaoImpl studentRepo;

    public absenceManagerService(StudentDaoImpl studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudentsList() throws SQLException {
        return studentRepo.readStudentList();
    }
}
