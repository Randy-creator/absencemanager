package com.hei.absencemanager.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Course {
    private int courseId;
    private String courseName;
    private Teacher teacher;
    private List<isAbsent> studentAbsentList;

    public Course(int courseId, String courseName, Teacher teacher, List<isAbsent> studentAbsentList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacher;
        this.studentAbsentList = studentAbsentList;
    }
}
