package com.ruitx.formation.controller;

import com.ruitx.formation.dto.course.CourseCreationDTO;
import com.ruitx.formation.dto.course.CourseDTO;
import com.ruitx.formation.service.CourseService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CourseControllerRestTest {

    private static final Logger logger = LoggerFactory.getLogger(CourseControllerRestTest.class);

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    public void setup() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            RestAssuredMockMvc.standaloneSetup(courseController);
        } catch (Exception e) {
            logger.error("Error setting up mocks", e);
        }
    }

    @Test
    public void gettingAllCourses_shouldSucceed() {
        List<CourseDTO> courses = Arrays.asList(
                new CourseDTO(1L, "Math 101", "Math 101 description", 10, "Math 101 teacher", 30),
                new CourseDTO(2L, "Physics 101", "Physics 101 description", 10, "Physics 101 teacher", 30)
        );

        when(courseService.getAll()).thenReturn(courses);

        MockMvcResponse response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/courses/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<CourseDTO> returnedCourseList = response.jsonPath().getList("", CourseDTO.class);

        assertEquals(returnedCourseList.size(), courses.size());
        for (int i = 0; i < returnedCourseList.size(); i++) {
            assertEquals(returnedCourseList.get(i).id(), courses.get(i).id());
            assertEquals(returnedCourseList.get(i).name(), courses.get(i).name());
            assertEquals(returnedCourseList.get(i).description(), courses.get(i).description());
            assertEquals(returnedCourseList.get(i).credits(), courses.get(i).credits());
            assertEquals(returnedCourseList.get(i).teacher(), courses.get(i).teacher());
            assertEquals(returnedCourseList.get(i).maxStudents(), courses.get(i).maxStudents());
        }
    }

    @Test
    public void gettingCourseById_shouldSucceed() {
        CourseDTO course = new CourseDTO(1L, "Math 101", "Math 101 description", 10, null, 30);

        when(courseService.get(1L)).thenReturn(course);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/courses/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Math 101"));
    }

    @Test
    public void creatingCourse_shouldSucceed() {
        CourseCreationDTO courseCreationDTO = new CourseCreationDTO("Math 101", "Math 101 description", 10, "Math 101 teacher", 30);
        CourseDTO courseDTO = new CourseDTO(1L, "Math 101", "Math 101 description", 10, "Math 101 teacher", 30);

        when(courseService.create(courseCreationDTO)).thenReturn(courseDTO);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(courseCreationDTO)
                .when()
                .post("/api/v1/courses/")
                .then()
                .statusCode(201)
                .body("id", equalTo(1))
                .body("name", equalTo("Math 101"))
                .body("description", equalTo("Math 101 description"))
                .body("credits", equalTo(10))
                .body("teacher", equalTo("Math 101 teacher"))
                .body("maxStudents", equalTo(30));
    }

    @Test
    public void deletingCourse_shouldSucceed() {
        doNothing().when(courseService).delete(1L);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/v1/courses/1")
                .then()
                .statusCode(204);
    }
}
