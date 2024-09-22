-- SELECT
--     s.STD,
INSERT INTO
    isAbsent (isJustified, stdRef, courseId, date, attendId)
SELECT
    false,
    A.stdRef,
    A.courseId,
    A.date,
    A.attendId
FROM
    Attend A
WHERE
    A.presenceStatus = 'a';

-- Select records where the student was absent ('a')
SELECT 
    s.STD,
    s.firstName,
    s.lastName,
    a.isJustified,
    a.date,
    s.CORstatus,
    c.courseName
FROM 
    isAbsent a
JOIN 
    Student s ON a.stdRef = s.STD
JOIN 
    Course c ON a.courseId = c.courseId;
