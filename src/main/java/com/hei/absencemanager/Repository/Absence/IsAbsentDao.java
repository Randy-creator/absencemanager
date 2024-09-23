package com.hei.absencemanager.Repository.Absence;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.hei.absencemanager.Entity.IsAbsent;

public interface IsAbsentDao {
    public List<IsAbsent> readAbsenceList() throws SQLException;

    public List<IsAbsent> readOneStudentsAbsences(String std) throws SQLException;

    public void updateCORStatusForUnjustifiedAbsences() throws SQLException;

}
