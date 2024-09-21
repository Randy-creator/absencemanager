package com.hei.absencemanager.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String std;
    private String email;
    private String firstName;
    private String lastName;
    private String group;
    private Boolean CORstatus;

    public Student(String std, String email, String firstName, String lastName, String group, Boolean CORstatus) {
        this.std = std;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.CORstatus = CORstatus;
    }

    @Override
    public String toString() {
        return "Student{" +
                "std='" + std + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                ", CORstatus=" + CORstatus +
                '}';
    }
}
