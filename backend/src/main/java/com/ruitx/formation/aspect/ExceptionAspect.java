package com.ruitx.formation.aspect;

import com.ruitx.formation.exceptions.Error;
import com.ruitx.formation.exceptions.ErrorMessage;
import com.ruitx.formation.exceptions.course.CourseNotFoundException;
import com.ruitx.formation.exceptions.courseEntry.CourseEntryAlreadyExistsException;
import com.ruitx.formation.exceptions.courseEntry.CourseEntryNotFoundException;
import com.ruitx.formation.exceptions.student.StudentNotFoundException;
import com.ruitx.formation.exceptions.teacher.TeacherNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;

@Component
@ControllerAdvice
public class ExceptionAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @ExceptionHandler({
            StudentNotFoundException.class,
            CourseNotFoundException.class,
            CourseEntryNotFoundException.class,
            TeacherNotFoundException.class
    })
    public ResponseEntity<String> HandleNotFoundException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.HANDLE_NOT_FOUND, e.getMessage());

        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            CourseEntryAlreadyExistsException.class
    })
    public ResponseEntity<String> HandleAlreadyExistsException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.HANDLE_ALREADY_EXISTS, e.getMessage());
        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error.toJson(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<String> HandleNoResourceFoundException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.HANDLE_NO_RESOURCE_FOUND, e.getMessage());
        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error.toJson(), HttpStatus.NOT_FOUND);
    }

    // generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> GenericException(Exception e, HttpServletRequest request) {
        logger.error("{}: {}", ErrorMessage.GENERIC_ERROR, e.getMessage());

        Error error = new Error.Builder()
                .timestamp(new Date().toString())
                .message(e.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error.toJson(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
