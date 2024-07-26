package com.ruitx.formation.controller;

import com.ruitx.formation.dto.courseEntry.CourseEntryCreationDTO;
import com.ruitx.formation.dto.courseEntry.CourseEntryDTO;
import com.ruitx.formation.service.CourseEntryService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

public class CourseEntryControllerRestTest {

    private static final Logger logger = LoggerFactory.getLogger(CourseControllerRestTest.class);

    @Mock
    private CourseEntryService courseEntryService;

    @InjectMocks
    private CourseEntryController courseEntryController;

    @BeforeEach
    public void setup() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            RestAssuredMockMvc.standaloneSetup(courseEntryController);
        } catch (Exception e) {
            logger.error("Error setting up mocks", e);
        }
    }

    @Test
    @Disabled
    public void gettingAllCourseEntries_shouldSucceed() {
        List<CourseEntryDTO> courseEntries = Arrays.asList(
                new CourseEntryDTO(1L, 1L, 1L, 10f, LocalDate.now(), true),
                new CourseEntryDTO(2L, 2L, 2L, 10f, LocalDate.now(), true)
        );

        when(courseEntryService.getAll()).thenReturn(courseEntries);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/entries/")
                .then()
                .statusCode(201)
                .body("[0].id", equalTo(1L))
                .body("[0].studentId", equalTo(1L))
                .body("[0].courseId", equalTo(1L))
                .body("[0].grade", equalTo(10f))
                .body("[0].dateRegistered", equalTo(LocalDate.now()))
                .body("[0].isActive", equalTo(true))

                .body("[1].id", equalTo(2L))
                .body("[1].studentId", equalTo(2L))
                .body("[1].courseId", equalTo(2L))
                .body("[1].grade", equalTo(10f))
                .body("[1].dateRegistered", equalTo(LocalDate.now()))
                .body("[1].isActive", equalTo(true));
    }

    @Test
    @Disabled
    public void gettingCourseEntryById_shouldSucceed() {
        CourseEntryDTO courseEntry = new CourseEntryDTO(1L, 1L, 1L, 10f, LocalDate.now(), true);

        when(courseEntryService.get(1L)).thenReturn(courseEntry);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/entries/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1L))
                .body("name", equalTo("Entry 1"))
                .body("studentId", equalTo(1L))
                .body("courseId", equalTo(1L))
                .body("grade", equalTo(10f))
                .body("dateRegistered", equalTo(LocalDate.now()))
                .body("isActive", equalTo(true));
    }

    @Test
    @Disabled
    public void creatingCourseEntry_shouldSucceed() {
        CourseEntryCreationDTO courseEntryCreationDTO = new CourseEntryCreationDTO(1L, 1L, 10f, LocalDate.now(), true);
        CourseEntryDTO courseEntryDTO = new CourseEntryDTO(1L, 1L, 1L, 10f, LocalDate.now(), true);

        when(courseEntryService.create(courseEntryCreationDTO)).thenReturn(courseEntryDTO);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(courseEntryCreationDTO)
                .when()
                .post("/api/v1/entries/")
                .then()
                .statusCode(200)
                .body("id", equalTo(1L))
                .body("name", equalTo("Entry 1"))
                .body("studentId", equalTo(1L))
                .body("courseId", equalTo(1L))
                .body("grade", equalTo(10f))
                .body("dateRegistered", equalTo(LocalDate.now()))
                .body("isActive", equalTo(true));
    }

    @Test
    @Disabled
    public void creatingCourseDuplicateEntry_shouldFail() {
        CourseEntryCreationDTO courseEntryCreationDTO = new CourseEntryCreationDTO(1L, 1L, 10f, LocalDate.now(), true);
        CourseEntryDTO courseEntryDTO = new CourseEntryDTO(1L, 1L, 1L, 10f, LocalDate.now(), true);

        //when(courseEntryService.create(courseEntryCreationDTO)).thenReturn(courseEntryDTO);


    /*sertThrows(CourseEntryAlreadyExistsException.class, () -> {
            courseEntryService.create(courseEntryCreationDTO);
            courseEntryService.create(courseEntryCreationDTO);
        });


     */
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(courseEntryCreationDTO)
                .when()
                .post("/api/v1/entries/")
                .then()
                .statusCode(409)
                .body("message", equalTo("A course entry with this student id and course id already exists"));
    }
}
