package com.hei.absencemanager.Entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class Proof {
    private LocalDate date;
    private String reason;

    public Proof(LocalDate date, String reason) {
        this.date = date;
        this.reason = reason;
    }
}
