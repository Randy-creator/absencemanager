package com.hei.absencemanager.Controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.hei.absencemanager.Entity.IsAbsent;
import com.hei.absencemanager.Service.IsAbsentServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class isAbsentController {
    private IsAbsentServices isAbsentService;

    public isAbsentController(IsAbsentServices isAbsentService) {
        this.isAbsentService = isAbsentService;
    }

    @GetMapping("absences")
    public List<IsAbsent> readAbsenceList() throws SQLException {
        return isAbsentService.readAbsenceList();
    }

    @GetMapping("absences/{std}")
    public List<IsAbsent> readOneStudentsAbsences(@PathVariable String std) throws SQLException {
        return isAbsentService.readOneStudentsAbsences(std);
    }

    @PutMapping("absences/update")
    public void updateCORStatusForUnjustifiedAbsences() throws SQLException {
        isAbsentService.updateCORStatusForUnjustifiedAbsences();
    }
}
