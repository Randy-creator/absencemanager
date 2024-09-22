package com.hei.absencemanager.Repository.AttendanceList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.hei.absencemanager.Entity.Attend;

public interface AttendanceListDao {
    public List<Attend> readAttendanceList() throws SQLException;

    public void markAttendance(Attend attend) throws SQLException;

}
