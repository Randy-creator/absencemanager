package com.hei.absencemanager.Entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class isAbsent {
    private int attendanceId;
    private boolean isJustified;
    private LocalDateTime date;

    public isAbsent(int attendanceId, boolean isJustified, LocalDateTime date) {
        this.attendanceId = attendanceId;
        this.isJustified = isJustified;
        this.date = date;
    }

}
