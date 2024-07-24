package com.ruitx.formation.exceptions;

public class CourseEntryAlreadyExistsException extends RuntimeException {
    public CourseEntryAlreadyExistsException() {
        super("A course entry with this student id and course id already exists");
    }

    public CourseEntryAlreadyExistsException(String message) {
        super(message);
    }
}
