package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Repository.StudentDaoImpl;

@Service
public class studentServices {
    private StudentDaoImpl studentRepo;

    public studentServices(StudentDaoImpl studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudentsList() throws SQLException {
        return studentRepo.readStudentList();
    }

    public Student getOneStudent(String std) throws SQLException {
        return studentRepo.searchOneStudent(std);
    }

    public Student createStudent(Student toCreate) throws SQLException {
        return studentRepo.createStudent(toCreate);
    }

    public List<Student> findStudents(String toSearch) throws SQLException {
        return studentRepo.searchStudentName(toSearch);
    }
}
