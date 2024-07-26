package com.ruitx.formation.exceptions;

public class ErrorMessage {
    public static final String STUDENT_NOT_FOUND = "Student not found";

    public static final String COURSE_NOT_FOUND = "Course not found";

    public static final String COURSE_ENTRY_NOT_FOUND = "Course entry not found";
    public static final String COURSE_ENTRY_ALREADY_EXISTS = "A course entry with this student id and course id already exists";

    public static final String TEACHER_NOT_FOUND = "Teacher not found";
    public static final String TEACHER_ALREADY_EXISTS = "A teacher with this name already exists";
    public static final String TEACHER_COURSE_ALREADY_EXISTS = "A teacher with this name already exists";

    public static final String COURSE_TEACHER_GROUP_NOT_FOUND = "Course teacher group Id not found";

    public static final String CLASS_NOT_FOUND = "Class not found";

    public static final String HANDLE_NOT_FOUND = "Handle not found";
    public static final String HANDLE_ALREADY_EXISTS = "Duplicate entity found";
    public static final String HANDLE_NO_RESOURCE_FOUND = "The requested resource was not found";
    public static final String INVALID_ARGUMENT = "An invalid argument was provided";
    public static final String JSON_PARSING_ERROR = "An error occurred making the JSON response. Please try again later";
    public static final String GENERIC_ERROR = "An internal error occurred, please try again later";
}
