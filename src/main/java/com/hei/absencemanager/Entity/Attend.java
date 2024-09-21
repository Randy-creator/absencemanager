package com.hei.absencemanager.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attend {
    private String std;
    private String firstName;
    private String lastName;
    private String courseName;
    private LocalDateTime date;
    private char presenceStatus;

    public Attend(String std, String firstName, String lastName, String courseName, LocalDateTime date,
            char presenceStatus) {
        this.std = std;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseName = courseName;
        this.date = date;
        this.presenceStatus = presenceStatus;
    }

}
