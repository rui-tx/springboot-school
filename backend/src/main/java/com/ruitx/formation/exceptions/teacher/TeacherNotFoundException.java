package com.ruitx.formation.exceptions.teacher;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException() {
        super("Teacher not found");
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
