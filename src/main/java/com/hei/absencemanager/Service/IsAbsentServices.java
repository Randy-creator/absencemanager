package com.hei.absencemanager.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hei.absencemanager.Entity.IsAbsent;
import com.hei.absencemanager.Repository.Absence.IsAbsentDaoImpl;

@Service
public class IsAbsentServices {
    private IsAbsentDaoImpl isAbsentRepo;

    public IsAbsentServices(IsAbsentDaoImpl isAbsentRepo) {
        this.isAbsentRepo = isAbsentRepo;
    }

    public List<IsAbsent> readAbsenceList() throws SQLException {
        return isAbsentRepo.readAbsenceList();
    }
}
