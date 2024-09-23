package com.hei.absencemanager.Entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Proof {
    private int proofId;
    private String std;
    private String courseName;
    private LocalDateTime date;
    private String reason;

    public Proof(int proofId, String std, String courseName, LocalDateTime date, String reason) {
        this.proofId = proofId;
        this.std = std;
        this.courseName = courseName;
        this.date = date;
        this.reason = reason;
    }

    // INSERT INTO

    // Proof (date, reason, attendanceId)
    // SELECT date, 'Medical certificate', attendanceId
    // FROM isAbsent
    // WHERE isJustified = true;

    // // requete :
    // SELECT
    // Proof.proofId,
    // Student.STD AS std,Course.courseName,Proof.date,
    // Proof.reason
    // FROM
    // Proof
    // JOIN
    // isAbsent ON Proof.attendanceId=
    // isAbsent.attendanceId JOIN
    // Student ON isAbsent.stdRef=
    // Student.STD JOIN
    // Course ON isAbsent.courseId=Course.courseId;

}
