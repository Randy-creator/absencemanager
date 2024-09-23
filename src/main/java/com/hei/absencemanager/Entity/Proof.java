package com.hei.absencemanager.Entity;

import lombok.Getter;
import lombok.Setter;
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
}
