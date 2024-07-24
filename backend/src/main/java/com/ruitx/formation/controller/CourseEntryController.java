package com.ruitx.formation.controller;

import com.ruitx.formation.dto.CourseEntryCreationDTO;
import com.ruitx.formation.dto.CourseEntryDTO;
import com.ruitx.formation.service.CourseEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/entries")
public class CourseEntryController {
    private final CourseEntryService courseEntryService;

    @Autowired
    public CourseEntryController(CourseEntryService courseEntryService) {
        this.courseEntryService = courseEntryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseEntryDTO>> getAll() {
        List<CourseEntryDTO> courseEntryDTOList = courseEntryService.getAll();
        return ResponseEntity.ok(courseEntryDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntryDTO> getEntryById(@PathVariable Long id) {
        CourseEntryDTO courseEntryDTO = courseEntryService.get(id);
        return ResponseEntity.ok(courseEntryDTO);
    }

    @PostMapping("/")
    public ResponseEntity<CourseEntryDTO> createCourse(@RequestBody CourseEntryCreationDTO courseEntryCreationDTO) {
        CourseEntryDTO courseEntryDTOCreated = courseEntryService.create(courseEntryCreationDTO);
        return ResponseEntity.ok(courseEntryDTOCreated);
    }

}
