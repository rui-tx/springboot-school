package com.ruitx.formation.exceptions.classExp;

public class ClassNotFoundException extends RuntimeException {
    public ClassNotFoundException() {
        super("Class not found");
    }

    public ClassNotFoundException(String message) {
        super(message);
    }
}
