package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.AbsenceRequest;
import com.hei.absencemanager.Entity.Attend;
import com.hei.absencemanager.Repository.AttendanceList.AttendanceListDaoImpl;

@Service
public class AttendanceListServices {
    private AttendanceListDaoImpl attendanceListRepo;

    public AttendanceListServices(AttendanceListDaoImpl attendanceListRepo) {
        this.attendanceListRepo = attendanceListRepo;
    }

    public List<Attend> readAttendanceList() throws SQLException {
        return attendanceListRepo.readAttendanceList();
    }

    public List<Attend> getStudentsByCourseAndDate(String courseName, LocalDateTime date) throws SQLException {
        return attendanceListRepo.getStudentsByCourseAndDate(courseName, date);
    }

    public List<Attend> getAttendanceByStudent(String stdRef) throws SQLException {
        return attendanceListRepo.getAttendanceByStudent(stdRef);
    }

    public void deleteAttendance(String stdRef, String courseName, LocalDateTime date) throws SQLException {
        attendanceListRepo.deleteAttendance(stdRef, courseName, date);
    }

    public void updateAttendance(String stdRef, String courseName, LocalDateTime date, char newStatus)
            throws SQLException {
        attendanceListRepo.updateAttendance(stdRef, courseName, date, newStatus);
    }

    public List<Attend> getAttendanceByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        return attendanceListRepo.getAttendanceByDateRange(startDate, endDate);
    }

    public List<Attend> getAllAttendanceByCourse(String courseName) throws SQLException {
        return attendanceListRepo.getAllAttendanceByCourse(courseName);
    }

    public Attend createAttendance(Attend attend) throws SQLException {
        return attendanceListRepo.createAttendance(attend);
    }

    public void presentUpdate(AbsenceRequest listOfAbsent, LocalDateTime date, String courseName) throws SQLException {
        attendanceListRepo.presentUpdate(listOfAbsent, date, courseName);
    }
}
