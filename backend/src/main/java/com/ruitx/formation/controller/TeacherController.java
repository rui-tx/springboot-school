package com.ruitx.formation.controller;

import com.ruitx.formation.dto.teacher.TeacherCreationDTO;
import com.ruitx.formation.dto.teacher.TeacherDTO;
import com.ruitx.formation.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.ruitx.formation.config.Constants.TEACHER_MAPPING;

@Tag(name = "Teacher", description = "Teacher API")
@RestController
@RequestMapping(TEACHER_MAPPING)
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "List all teachers")
    @GetMapping("/")
    public ResponseEntity<List<TeacherDTO>> getAll() {
        List<TeacherDTO> teacherDTOList = teacherService.getAll();
        return ResponseEntity.ok(teacherDTOList);
    }

    @Operation(summary = "Get a teacher by id")
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        TeacherDTO teacherDTO = teacherService.get(id);
        return ResponseEntity.ok(teacherDTO);
    }

    @Operation(summary = "Create a teacher")
    @PostMapping("/")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherCreationDTO teacherCreationDTO) {
        TeacherDTO teacherDTOCreated = teacherService.create(teacherCreationDTO);
        return ResponseEntity.created(URI.create("/api/v1/teachers/" + teacherDTOCreated.id())).body(teacherDTOCreated);
    }

    @Operation(summary = "Update a teacher")
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherCreationDTO teacherCreationDTO) {
        TeacherDTO teacherDTOUpdated = teacherService.update(id, teacherCreationDTO);
        return ResponseEntity.ok(teacherDTOUpdated);
    }

    @Operation(summary = "Delete a teacher")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
