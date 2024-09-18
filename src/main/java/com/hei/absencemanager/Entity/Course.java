package com.hei.absencemanager.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Course {
    private int courseId;
    private String courseName;
    private List<isAbsent> studentAbsentList;

    public Course(int courseId, String courseName, List<isAbsent> studentAbsentList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentAbsentList = studentAbsentList;
    }
}
