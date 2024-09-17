package com.hei.absencemanager.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher {
    private String firstName;
    private String lastName;
    private int teacherRef;

    public Teacher(String firstName, String lastName, int teacherRef) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacherRef = teacherRef;
    }
}
