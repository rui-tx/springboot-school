package com.ruitx.formation.controller;

import com.ruitx.formation.dto.StudentCreationDTO;
import com.ruitx.formation.dto.StudentDTO;
import com.ruitx.formation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> studentDTOList = studentService.getAll();
        return ResponseEntity.ok(studentDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.get(id);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping("/")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentCreationDTO studentDTO) {
        StudentDTO studentDTOCreated = studentService.create(studentDTO);
        return ResponseEntity.ok(studentDTOCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentCreationDTO studentCreationDTO) {
        StudentDTO studentDTOUpdated = studentService.update(id, studentCreationDTO);
        return ResponseEntity.ok(studentDTOUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
