package com.hei.absencemanager.Repository.Absence;

import java.sql.SQLException;
import java.util.List;

import com.hei.absencemanager.Entity.IsAbsent;

public interface IsAbsentDao {
    public List<IsAbsent> readAbsenceList() throws SQLException;
}
