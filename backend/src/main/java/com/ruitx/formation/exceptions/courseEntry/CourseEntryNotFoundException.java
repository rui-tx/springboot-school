package com.ruitx.formation.exceptions.courseEntry;

public class CourseEntryNotFoundException extends RuntimeException {
    public CourseEntryNotFoundException() {
        super("Course entry not found");
    }

    public CourseEntryNotFoundException(String message) {
        super(message);
    }
}
