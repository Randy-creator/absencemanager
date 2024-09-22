package com.hei.absencemanager.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.hei.absencemanager.Entity.AbsenceRequest;
import com.hei.absencemanager.Entity.Attend;
import com.hei.absencemanager.Service.AttendanceListServices;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class AttendanceListController {
    private AttendanceListServices attendanceListService;

    public AttendanceListController(AttendanceListServices attendanceListService) {
        this.attendanceListService = attendanceListService;
    }

    @GetMapping("attendance")
    public List<Attend> readAttendanceList() throws SQLException {
        return attendanceListService.readAttendanceList();
    }

    @GetMapping("attendance/course")
    public List<Attend> getStudentsByCourseAndDate(@RequestParam String courseName, @RequestParam String date)
            throws SQLException {
        LocalDateTime attendanceDate = LocalDateTime.parse(date);
        return attendanceListService.getStudentsByCourseAndDate(courseName, attendanceDate);
    }

    @GetMapping("attendance/{stdRef}")
    public List<Attend> getAttendanceByStudent(@PathVariable String stdRef) throws SQLException {
        return attendanceListService.getAttendanceByStudent(stdRef);
    }

    @DeleteMapping("attendance/{stdRef}")
    public void deleteAttendance(
            @PathVariable String stdRef,
            @RequestParam String courseName,
            @RequestParam LocalDateTime date) throws SQLException {
        attendanceListService.deleteAttendance(stdRef, courseName, date);
    }

    @PutMapping("attendance/{stdRefs}")
    public void updateAttendance(
            @PathVariable String stdRefs,
            @RequestParam String courseName,
            @RequestParam LocalDateTime date,
            @RequestParam char newStatus)
            throws SQLException {
        attendanceListService.updateAttendance(stdRefs, courseName, date, newStatus);
    }

    @GetMapping("attendance/date")
    public List<Attend> getAttendanceByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) throws SQLException {
        return attendanceListService.getAttendanceByDateRange(startDate, endDate);
    }

    @GetMapping("attendance/course/{courseName}")
    public List<Attend> getAllAttendanceByCourse(@PathVariable String courseName) throws SQLException {
        return attendanceListService.getAllAttendanceByCourse(courseName);
    }

    @PostMapping("attendance/student")
    public Attend createAttendance(@RequestBody Attend attend) throws SQLException {
        return attendanceListService.createAttendance(attend);
    }

    @PostMapping("attendance/presence")
    public void presenceUpdate(
            @RequestBody AbsenceRequest listOfAbsent,
            @RequestParam String courseName,
            @RequestParam LocalDateTime date) throws SQLException {

        attendanceListService.presentUpdate(listOfAbsent, date, courseName);
    }
}
