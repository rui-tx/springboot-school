package com.ruitx.formation.controller;

import com.ruitx.formation.dto.course.CourseCreationDTO;
import com.ruitx.formation.dto.courseEntry.CourseEntryCreationDTO;
import com.ruitx.formation.dto.student.StudentCreationDTO;
import com.ruitx.formation.dto.teacher.TeacherCreationDTO;
import com.ruitx.formation.service.CourseEntryService;
import com.ruitx.formation.service.CourseService;
import com.ruitx.formation.service.StudentService;
import com.ruitx.formation.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
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
    private final CourseEntryService courseEntryService;

    @Autowired
    public AdminController(CourseService courseService, StudentService studentService,
                           TeacherService teacherService, CourseEntryService courseEntryService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.courseEntryService = courseEntryService;
    }

    @Cacheable(value = "randomUserId", unless = "#result == null")
    @GetMapping("/randomUser")
    public ResponseEntity<String> randomUser() {
        RestClient restClient = RestClient.create();
        String result = restClient.get()
                .uri("https://randomuser.me/api/?results=1")
                .retrieve()
                .body(String.class);

        return ResponseEntity.ok(result);
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

        for (int i = 0; i < entityNumber; i++) {
            long randomStudentId = new Random().nextLong(entityNumber);
            long randomCourseId = new Random().nextLong(entityNumber);
            float randomGrade = new Random().nextFloat();
            LocalDate randomDateRegistered = LocalDate.now();
            boolean randomIsActive = new Random().nextBoolean();
            CourseEntryCreationDTO courseEntry = new CourseEntryCreationDTO(
                    randomStudentId,
                    randomCourseId,
                    randomGrade,
                    randomDateRegistered,
                    randomIsActive
            );
            courseEntryService.create(courseEntry);
        }

        return ResponseEntity.noContent().build();
    }
}
