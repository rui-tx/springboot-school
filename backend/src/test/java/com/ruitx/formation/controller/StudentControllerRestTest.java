package com.ruitx.formation.controller;

import com.ruitx.formation.dto.student.StudentCreationDTO;
import com.ruitx.formation.dto.student.StudentDTO;
import com.ruitx.formation.service.StudentService;
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

public class StudentControllerRestTest {

    private static final Logger logger = LoggerFactory.getLogger(CourseControllerRestTest.class);

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            RestAssuredMockMvc.standaloneSetup(studentController);
        } catch (Exception e) {
            logger.error("Error setting up mocks", e);
        }
    }

    @Test
    public void gettingAllStudents_shouldSucceed() {
        List<StudentDTO> students = Arrays.asList(
                new StudentDTO(1L, "John Doe", "john@gmail.com", "+123456789", 20, "M"),
                new StudentDTO(2L, "Jane Smith", "jane@gmail.com", "+987654321", 21, "F")
        );

        when(studentService.getAll()).thenReturn(students);

        MockMvcResponse response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/students/")
                .then()
                .extract()
                .response();

        List<StudentDTO> returnedStudentList = response.jsonPath().getList("", StudentDTO.class);

        assertEquals(returnedStudentList.size(), students.size());
        for (int i = 0; i < returnedStudentList.size(); i++) {
            assertEquals(returnedStudentList.get(i).id(), students.get(i).id());
            assertEquals(returnedStudentList.get(i).name(), students.get(i).name());
            assertEquals(returnedStudentList.get(i).email(), students.get(i).email());
            assertEquals(returnedStudentList.get(i).phone(), students.get(i).phone());
            assertEquals(returnedStudentList.get(i).age(), students.get(i).age());
            assertEquals(returnedStudentList.get(i).gender(), students.get(i).gender());
        }
    }

    @Test
    public void gettingStudentById_shouldSucceed() {
        StudentDTO student = new StudentDTO(1L, "John Doe", "john@gmail.com", "+123456789", 20, "M");

        when(studentService.get(1L)).thenReturn(student);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/students/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john@gmail.com"))
                .body("phone", equalTo("+123456789"))
                .body("age", equalTo(20))
                .body("gender", equalTo("M"));
    }

    @Test
    public void creatingStudent_shouldSucceed() {
        StudentCreationDTO studentCreationDTO = new StudentCreationDTO("John Doe", "john@gmail.com", "+123456789", 20, "M");
        StudentDTO studentDTO = new StudentDTO(1L, "John Doe", "john@gmail.com", "+123456789", 20, "M");

        when(studentService.create(studentCreationDTO)).thenReturn(studentDTO);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(studentCreationDTO)
                .when()
                .post("/api/v1/students/")
                .then()
                .statusCode(201)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john@gmail.com"))
                .body("phone", equalTo("+123456789"))
                .body("age", equalTo(20))
                .body("gender", equalTo("M"));
    }

    @Test
    public void updatingStudent_shouldSucceed() {
        StudentCreationDTO studentCreationDTO = new StudentCreationDTO("John Doe Updated", "john@gmail.com", "+123456789", 20, "M");
        StudentDTO studentDTOUpdated = new StudentDTO(1L, "John Doe Updated", "john@gmail.com", "+123456789", 20, "M");

        when(studentService.update(1L, studentCreationDTO)).thenReturn(studentDTOUpdated);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(studentCreationDTO)
                .when()
                .put("/api/v1/students/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("John Doe Updated"));
    }

    @Test
    public void deletingStudent_shouldSucceed() {
        doNothing().when(studentService).delete(1L);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/api/v1/students/1")
                .then()
                .statusCode(204);
    }
}