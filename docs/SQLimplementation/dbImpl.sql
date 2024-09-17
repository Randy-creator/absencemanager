CREATE TABLE Student (
    STD varchar(8) PRIMARY KEY,
    email varchar(150),
    firstName varchar(200),
    lastName varchar(200),
    "group" varchar(2),
    CORstatus boolean
);

CREATE TABLE Teacher (
    firstName varchar(200),
    lastName varchar(200),
    teacherRef int PRIMARY KEY
);

CREATE TABLE Course (
    courseId int PRIMARY KEY,
    courseName varchar(150)
);

CREATE TABLE Attend (
    attendanceId SERIAL PRIMARY KEY,
    stdRef varchar(8),
    course_id int,
    CONSTRAINT fk_student FOREIGN KEY (stdRef) REFERENCES Student(STD) ON DELETE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES Course(courseId) ON DELETE CASCADE
);

CREATE TABLE isAbsent (
    absenceId SERIAL PRIMARY KEY,
    isJustified boolean,
    stdRef varchar(8),
    course_id int,
    CONSTRAINT fk_student FOREIGN KEY (stdRef) REFERENCES Student(STD) ON DELETE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES Course(courseId) ON DELETE CASCADE
);

CREATE TABLE Proof (
    proofId SERIAL PRIMARY KEY,
    date DATE,
    reason varchar(255),
    absenceId int,
    CONSTRAINT fk_absence FOREIGN KEY (absenceId) REFERENCES isAbsent(absenceId) ON DELETE CASCADE
);