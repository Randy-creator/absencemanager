package com.hei.absencemanager.Repository.AttendanceList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.hei.absencemanager.Entity.Attend;

public interface AttendanceListDao {
    public List<Attend> readAttendanceList() throws SQLException;

    public List<Attend> getStudentsByCourseAndDate(String courseName, LocalDateTime date) throws SQLException;

    public List<Attend> getAttendanceByStudent(String stdRef) throws SQLException;

    public void deleteAttendance(String stdRef, String courseName, LocalDateTime date) throws SQLException;

    public void updateAttendance(String stdRef, String courseName, LocalDateTime date, char newStatus)
            throws SQLException;

    public List<Attend> getAttendanceByDateRange(LocalDateTime startDate, LocalDateTime endDate) throws SQLException;

}
