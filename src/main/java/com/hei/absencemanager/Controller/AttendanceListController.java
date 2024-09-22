package com.hei.absencemanager.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.hei.absencemanager.Entity.AbsenceRequest;
import com.hei.absencemanager.Entity.Attend;
import com.hei.absencemanager.Service.AttendanceListServices;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @PostMapping("attendance")
    public void markAttendance(@RequestBody Attend attend) throws SQLException {
        attendanceListService.markAttendance(attend);
    }

}
