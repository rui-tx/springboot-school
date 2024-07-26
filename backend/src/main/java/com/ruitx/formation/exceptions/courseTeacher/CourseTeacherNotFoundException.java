package com.ruitx.formation.exceptions.courseTeacher;

public class CourseTeacherNotFoundException extends RuntimeException {
    public CourseTeacherNotFoundException() {
        super("Course teacher group Id not found");
    }

    public CourseTeacherNotFoundException(String message) {
        super(message);
    }
}
