package com.ruitx.formation.controller;

import com.ruitx.formation.dto.StudentCreationDTO;
import com.ruitx.formation.dto.StudentDTO;
import com.ruitx.formation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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

}
