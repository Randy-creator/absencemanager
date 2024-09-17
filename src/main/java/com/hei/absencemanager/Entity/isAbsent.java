package com.hei.absencemanager.Entity;

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

    private int absenceId;
    private boolean isJustified;

    public isAbsent(int absenceId, boolean isJustified) {
        this.absenceId = absenceId;
        this.isJustified = isJustified;
    }
}
