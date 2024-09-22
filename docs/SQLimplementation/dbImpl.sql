CREATE TABLE Student (
    STD varchar(8) PRIMARY KEY,
    email varchar(150),
    firstName varchar(200),
    lastName varchar(200),
    "group" varchar(2),
    CORstatus boolean DEFAULT false
);

CREATE TABLE Teacher (
    teacherRef SERIAL PRIMARY KEY,
    firstName varchar(200),
    lastName varchar(200)
);

CREATE TABLE Course (
    courseId SERIAL PRIMARY KEY,
    courseName varchar(150)
);

CREATE TABLE Attend (
    attendId SERIAL PRIMARY KEY,
    stdRef varchar(8),
    courseId int,
    date timestamp,
    presenceStatus char(1) CHECK (presenceStatus IN ('p', 'a', 'q')),
    CONSTRAINT fk_student FOREIGN KEY (stdRef) REFERENCES Student(STD) ON DELETE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE
);

CREATE TABLE isAbsent (
    attendanceId SERIAL PRIMARY KEY,
    isJustified boolean,
    stdRef varchar(8),
    courseId int,
    date timestamp,
    attendId int,
    CONSTRAINT fk_student FOREIGN KEY (stdRef) REFERENCES Student(STD) ON DELETE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (courseId) REFERENCES Course(courseId) ON DELETE CASCADE,
    CONSTRAINT fk_attend FOREIGN KEY (attendId) REFERENCES Attend(attendId) ON DELETE CASCADE -- Foreign key constraint
);

CREATE TABLE Proof (
    proofId SERIAL PRIMARY KEY,
    date timestamp,
    reason varchar(255),
    attendanceId int,
    CONSTRAINT fk_attendance FOREIGN KEY (attendanceId) REFERENCES isAbsent(attendanceId) ON DELETE CASCADE
);