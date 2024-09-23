CREATE DATABASE absencemanager;

\ c absencemanager -- Create tables
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
    CONSTRAINT fk_attend FOREIGN KEY (attendId) REFERENCES Attend(attendId) ON DELETE CASCADE
);

CREATE TABLE Proof (
    proofId SERIAL PRIMARY KEY,
    date timestamp,
    reason varchar(255),
    attendanceId int,
    CONSTRAINT fk_attendance FOREIGN KEY (attendanceId) REFERENCES isAbsent(attendanceId) ON DELETE CASCADE
);

-- Insert data into Student table
INSERT INTO
    Student (STD, email, firstName, lastName, "group")
VALUES
    (
        'STD20001',
        'hei.alice.johnson@gmail.com',
        'Alice',
        'Johnson',
        'G1'
    ),
    (
        'STD20002',
        'hei.bob.smith@gmail.com',
        'Bob',
        'Smith',
        'J1'
    ),
    (
        'STD20003',
        'hei.charlie.williams@gmail.com',
        'Charlie',
        'Williams',
        'J2'
    ),
    (
        'STD20004',
        'hei.diana.brown@gmail.com',
        'Diana',
        'Brown',
        'H1'
    ),
    (
        'STD20005',
        'hei.ethan.jones@gmail.com',
        'Ethan',
        'Jones',
        'H2'
    ),
    (
        'STD20006',
        'hei.fiona.garcia@gmail.com',
        'Fiona',
        'Garcia',
        'G1'
    ),
    (
        'STD20007',
        'hei.george.miller@gmail.com',
        'George',
        'Miller',
        'J1'
    ),
    (
        'STD20008',
        'hei.hannah.davis@gmail.com',
        'Hannah',
        'Davis',
        'J2'
    ),
    (
        'STD20009',
        'hei.isaac.rodriguez@gmail.com',
        'Isaac',
        'Rodriguez',
        'H1'
    ),
    (
        'STD20010',
        'hei.julia.martinez@gmail.com',
        'Julia',
        'Martinez',
        'H2'
    ),
    (
        'STD20011',
        'hei.kevin.hernandez@gmail.com',
        'Kevin',
        'Hernandez',
        'G1'
    ),
    (
        'STD20012',
        'hei.lily.lopez@gmail.com',
        'Lily',
        'Lopez',
        'J1'
    ),
    (
        'STD20013',
        'hei.michael.gonzalez@gmail.com',
        'Michael',
        'Gonzalez',
        'J2'
    ),
    (
        'STD20014',
        'hei.nina.wilson@gmail.com',
        'Nina',
        'Wilson',
        'H1'
    ),
    (
        'STD20015',
        'hei.oscar.anderson@gmail.com',
        'Oscar',
        'Anderson',
        'H2'
    ),
    (
        'STD20016',
        'hei.paula.thomas@gmail.com',
        'Paula',
        'Thomas',
        'G1'
    ),
    (
        'STD20017',
        'hei.quincy.taylor@gmail.com',
        'Quincy',
        'Taylor',
        'J1'
    ),
    (
        'STD20018',
        'hei.rachel.moore@gmail.com',
        'Rachel',
        'Moore',
        'J2'
    ),
    (
        'STD20019',
        'hei.sam.jackson@gmail.com',
        'Sam',
        'Jackson',
        'H1'
    ),
    (
        'STD20020',
        'hei.tina.martin@gmail.com',
        'Tina',
        'Martin',
        'H2'
    );

-- Insert data into Course table
INSERT INTO
    Course (courseName)
VALUES
    ('PROG1'),
    ('SYS1'),
    ('WEB1'),
    ('DONNEE1'),
    ('PROG2'),
    ('SYS2'),
    ('DONNEE2'),
    ('WEB2');

-- Insert data into Attend table
INSERT INTO
    Attend (stdRef, courseId, date, presenceStatus)
VALUES
    ('STD20001', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20002', 1, '2024-09-20 10:00:00', 'a'),
    ('STD20003', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20004', 1, '2024-09-20 10:00:00', 'q'),
    ('STD20005', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20006', 1, '2024-09-20 10:00:00', 'a'),
    ('STD20007', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20008', 1, '2024-09-20 10:00:00', 'q'),
    ('STD20009', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20010', 1, '2024-09-20 10:00:00', 'a'),
    ('STD20011', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20012', 1, '2024-09-20 10:00:00', 'q'),
    ('STD20013', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20014', 1, '2024-09-20 10:00:00', 'a'),
    ('STD20015', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20016', 1, '2024-09-20 10:00:00', 'q'),
    ('STD20017', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20018', 1, '2024-09-20 10:00:00', 'a'),
    ('STD20019', 1, '2024-09-20 10:00:00', 'p'),
    ('STD20020', 1, '2024-09-20 10:00:00', 'q'),
    ('STD20001', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20002', 2, '2024-09-20 10:00:00', 'a'),
    ('STD20003', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20004', 2, '2024-09-20 10:00:00', 'q'),
    ('STD20005', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20006', 2, '2024-09-20 10:00:00', 'a'),
    ('STD20007', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20008', 2, '2024-09-20 10:00:00', 'q'),
    ('STD20009', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20010', 2, '2024-09-20 10:00:00', 'a'),
    ('STD20011', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20012', 2, '2024-09-20 10:00:00', 'q'),
    ('STD20013', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20014', 2, '2024-09-20 10:00:00', 'a'),
    ('STD20015', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20016', 2, '2024-09-20 10:00:00', 'q'),
    ('STD20017', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20018', 2, '2024-09-20 10:00:00', 'a'),
    ('STD20019', 2, '2024-09-20 10:00:00', 'p'),
    ('STD20020', 2, '2024-09-20 10:00:00', 'q'),
    ('STD20001', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20002', 3, '2024-09-20 10:00:00', 'a'),
    ('STD20003', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20004', 3, '2024-09-20 10:00:00', 'q'),
    ('STD20005', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20006', 3, '2024-09-20 10:00:00', 'a'),
    ('STD20007', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20008', 3, '2024-09-20 10:00:00', 'q'),
    ('STD20009', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20010', 3, '2024-09-20 10:00:00', 'a'),
    ('STD20011', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20012', 3, '2024-09-20 10:00:00', 'q'),
    ('STD20013', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20014', 3, '2024-09-20 10:00:00', 'a'),
    ('STD20015', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20016', 3, '2024-09-20 10:00:00', 'q'),
    ('STD20017', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20018', 3, '2024-09-20 10:00:00', 'a'),
    ('STD20019', 3, '2024-09-20 10:00:00', 'p'),
    ('STD20020', 3, '2024-09-20 10:00:00', 'q');

-- Insert data into isAbsent table
INSERT INTO
    isAbsent (isJustified, stdRef, courseId, date, attendId)
VALUES
    (true, 'STD20001', 1, '2024-09-21 10:00:00', 1),
    (false, 'STD20002', 1, '2024-09-21 10:00:00', 2),
    (true, 'STD20003', 1, '2024-09-21 10:00:00', 3),
    (false, 'STD20004', 1, '2024-09-21 10:00:00', 4),
    (true, 'STD20005', 1, '2024-09-21 10:00:00', 5),
    (false, 'STD20006', 1, '2024-09-21 10:00:00', 6),
    (true, 'STD20007', 1, '2024-09-21 10:00:00', 7),
    (false, 'STD20008', 1, '2024-09-21 10:00:00', 8),
    (true, 'STD20009', 1, '2024-09-21 10:00:00', 9),
    (false, 'STD20010', 1, '2024-09-21 10:00:00', 10);

-- Insert data into Proof table
INSERT INTO
    Proof (date, reason, attendanceId)
VALUES
    ('2024-09-21 10:00:00', 'Medical appointment', 1),
    ('2024-09-21 10:00:00', 'Family emergency', 2),
    ('2024-09-21 10:00:00', 'Illness', 3),
    ('2024-09-21 10:00:00', 'Vacation', 4),
    ('2024-09-21 10:00:00', 'Car trouble', 5);