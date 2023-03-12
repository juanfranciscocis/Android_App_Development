DROP TABLE faculty;
DROP TABLE courses;

CREATE TABLE faculty(
    facultyID VARCHAR(6) NOT NULL,
    facultyName VARCHAR(50) NOT NULL,
    office VARCHAR(50) NOT NULL,
    PRIMARY KEY (facultyID)
);

CREATE TABLE courses(
    courseID VARCHAR(50) NOT NULL,
    courseName VARCHAR(50) NOT NULL,
    facultyID VARCHAR(6) NOT NULL,
    PRIMARY KEY (courseID),
    FOREIGN KEY (facultyID) REFERENCES faculty(facultyID)
);

INSERT INTO faculty (facultyID, facultyName, office)
VALUES
    ('A52990', 'John Smith', 'MTC-218'),
    ('A77587', 'Sally Smith', 'MTC-320'),
    ('B66750', 'Bob Jones', 'MTC-257'),
    ('B78880', 'Sue Jones', 'MTC-211'),
    ('B86590', 'Fred Davis', 'MTC-214'),
    ('H99118', 'Ying Bai', 'MTC-336'),
    ('J33486', 'Steve Johnson', 'MTC-118'),
    ('K69880', 'Jenney King', 'MTC-324');

INSERT INTO courses (facultyID, courseName, courseID)
VALUES
    ('A52990', 'Data Structures', 'CSC-132A'),
    ('A77587', 'Programming 2', 'CSC-12'),
    ('B66750', 'Data Structures', 'CSC-123A'),
    ('H99118', 'Intro to Programming', 'CSC-456A'),
    ('H99118', 'Algorithms and Structures', 'CSC-132B'),
    ('H99118', 'Programming 1', 'CSC-135N'),
    ('J33486', 'Intro to Programming', 'CSC-234'),
    ('A52990', 'Programming 1', 'CSC-2345D');





