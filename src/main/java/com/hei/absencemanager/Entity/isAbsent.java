package com.hei.absencemanager.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class isAbsent {
    // dans le db : cette table prend les cles etrangeres de Student et Course
    // donc logiquement : il y a courseId et StudentId ici .
    // | courseId | studentId |
    // | 1 | 21 |
    // | 2 | 31 |
    // | 3 | 11 |

    private int attendanceId;
    private boolean isJustified;
    private LocalDateTime date;

    public isAbsent(int attendanceId, boolean isJustified, LocalDateTime date) {
        this.attendanceId = attendanceId;
        this.isJustified = isJustified;
        this.date = date;
    }

}
