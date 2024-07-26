package com.ruitx.formation.controller;

import com.ruitx.formation.dto.course.CourseCreationDTO;
import com.ruitx.formation.dto.student.StudentCreationDTO;
import com.ruitx.formation.dto.teacher.TeacherCreationDTO;
import com.ruitx.formation.service.CourseService;
import com.ruitx.formation.service.StudentService;
import com.ruitx.formation.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Random;

import static com.ruitx.formation.config.Constants.ADMIN_MAPPING;

@Tag(name = "Admin", description = "Admin API")
@RestController
@RequestMapping(ADMIN_MAPPING)
public class AdminController {
    private final CourseService courseService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public AdminController(CourseService courseService, StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/initDB")
    public ResponseEntity<Void> initDB() {
        int entityNumber = 2500;
        for (int i = 0; i < entityNumber; i++) {
            long randomPhoneNumber = new Random().nextLong(10000);
            int randomAge = new Random().nextInt(100);
            StudentCreationDTO student = new StudentCreationDTO(
                    "Student " + i,
                    "student" + i + "@gmail.com",
                    randomPhoneNumber + "",
                    randomAge,
                    "M"
            );
            studentService.create(student);
        }

        for (int i = 0; i < entityNumber; i++) {
            long randomPhoneNumber = new Random().nextLong(10000);
            int randomAge = new Random().nextInt(100);
            String randomDepartment = Arrays.asList("Computer Science", "Mathematics", "Physics").get(new Random().nextInt(3));
            TeacherCreationDTO teacher = new TeacherCreationDTO(
                    "Teacher " + i,
                    "teacher" + i + "@gmail.com",
                    randomPhoneNumber + "",
                    randomAge,
                    "M",
                    randomDepartment
            );
            teacherService.create(teacher);
        }

        for (int i = 0; i < entityNumber; i++) {
            long randomPhoneNumber = new Random().nextLong(10000);
            int randomCredits = new Random().nextInt(100);
            int randomMaxStudents = new Random().nextInt(100);
            String randomCourseName = Arrays.asList("Math 101", "Physics from Outer Space", "Algebra").get(new Random().nextInt(3));
            String randomCourseDesc = Arrays.asList("Now with more despair", "Intro class", "Fundamentals 2").get(new Random().nextInt(3));
            CourseCreationDTO course = new CourseCreationDTO(
                    randomCourseName,
                    randomCourseDesc,
                    randomCredits,
                    randomMaxStudents
            );
            courseService.create(course);
        }

        return ResponseEntity.noContent().build();
    }
}
