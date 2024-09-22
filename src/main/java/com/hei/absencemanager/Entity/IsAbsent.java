package com.hei.absencemanager.Entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IsAbsent {
    private String studentId;
    private boolean isJustified;
    private LocalDateTime absenceDate;
    private String proofReason;
    private LocalDateTime proofDate;
    private boolean corStatus;
    private String courseName;

    public IsAbsent(String studentId, boolean isJustified, LocalDateTime absenceDate, String proofReason,
            LocalDateTime proofDate, boolean corStatus, String courseName) {
        this.studentId = studentId;
        this.isJustified = isJustified;
        this.absenceDate = absenceDate;
        this.proofReason = proofReason;
        this.proofDate = proofDate;
        this.corStatus = corStatus;
        this.courseName = courseName;
    }
}

// SELECT
// s.STD AS StudentID,
// c.courseName AS CourseName,
// ia.isJustified AS IsJustified,
// ia.date AS AbsenceDate,
// p.reason AS ProofReason,
// p.date AS ProofDate,
// s.CORstatus AS CORStatus
// FROM
// isAbsent ia
// JOIN
// Student s ON ia.stdRef = s.STD
// JOIN
// Course c ON ia.courseId = c.courseId
// LEFT JOIN
// Proof p ON ia.attendanceId = p.attendanceId;