SELECT
    s.STD,
    s.firstName,
    s.lastName,
    c.courseName,
    a.date,
    a.presenceStatus
FROM
    Attend a
    JOIN Student s ON a.stdRef = s.STD
    JOIN Course c ON a.courseId = c.courseId
WHERE
    c.courseName = 'PROG1'
    AND
    a.date='2024-09-20 10:00:00' ;