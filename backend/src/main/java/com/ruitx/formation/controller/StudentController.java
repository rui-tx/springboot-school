package com.ruitx.formation.controller;

import com.ruitx.formation.dto.student.StudentCreationDTO;
import com.ruitx.formation.dto.student.StudentDTO;
import com.ruitx.formation.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Student", description = "Student API")
@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "List all students")
    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> studentDTOList = studentService.getAll();
        return ResponseEntity.ok(studentDTOList);
    }

    @Operation(summary = "Get a student by id")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.get(id);
        return ResponseEntity.ok(studentDTO);
    }

    @Operation(summary = "Create a student")
    @PostMapping("/")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentCreationDTO studentDTO) {
        StudentDTO studentDTOCreated = studentService.create(studentDTO);
        return ResponseEntity.created(URI.create("/api/v1/students/" + studentDTOCreated.id())).body(studentDTOCreated);
    }

    @Operation(summary = "Update a student")
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentCreationDTO studentCreationDTO) {
        StudentDTO studentDTOUpdated = studentService.update(id, studentCreationDTO);
        return ResponseEntity.ok(studentDTOUpdated);
    }

    @Operation(summary = "Delete a student")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
