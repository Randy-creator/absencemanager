package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.Student;
import com.hei.absencemanager.Repository.Student.StudentDaoImpl;

@Service
public class StudentServices {
    private StudentDaoImpl studentRepo;

    public StudentServices(StudentDaoImpl studentRepo) {
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

    public Student updateStudent(String std, Student toUpdate) throws SQLException {
        return studentRepo.updateStudentInfo(std, toUpdate);
    }

    public void deleteByStd(String std) throws SQLException {
        studentRepo.deleteByStd(std);
        ;
    }
}
