package com.ruitx.formation.exceptions.course;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found");
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
