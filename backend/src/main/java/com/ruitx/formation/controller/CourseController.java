package com.ruitx.formation.controller;

import com.ruitx.formation.dto.CourseCreationDTO;
import com.ruitx.formation.dto.CourseDTO;
import com.ruitx.formation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> courseDTOList = courseService.getAll();
        return ResponseEntity.ok(courseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.get(id);
        return ResponseEntity.ok(courseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseCreationDTO courseCreationDTO) {
        CourseDTO courseDTOCreated = courseService.create(courseCreationDTO);
        return ResponseEntity.ok(courseDTOCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
