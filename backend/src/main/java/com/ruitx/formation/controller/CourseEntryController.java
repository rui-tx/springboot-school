package com.ruitx.formation.controller;

import com.ruitx.formation.dto.courseEntry.CourseEntryCreationDTO;
import com.ruitx.formation.dto.courseEntry.CourseEntryDTO;
import com.ruitx.formation.service.CourseEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Entries", description = "Entries/Enrollments API")
@RestController
@RequestMapping("api/v1/entries")
public class CourseEntryController {
    private final CourseEntryService courseEntryService;

    @Autowired
    public CourseEntryController(CourseEntryService courseEntryService) {
        this.courseEntryService = courseEntryService;
    }

    @Operation(summary = "List all entries")
    @GetMapping("/")
    public ResponseEntity<List<CourseEntryDTO>> getAll() {
        List<CourseEntryDTO> courseEntryDTOList = courseEntryService.getAll();
        return ResponseEntity.ok(courseEntryDTOList);
    }

    @Operation(summary = "Get an entry by id")
    @GetMapping("/{id}")
    public ResponseEntity<CourseEntryDTO> getEntryById(@PathVariable Long id) {
        CourseEntryDTO courseEntryDTO = courseEntryService.get(id);
        return ResponseEntity.ok(courseEntryDTO);
    }

    @Operation(summary = "Create an entry")
    @PostMapping("/")
    public ResponseEntity<CourseEntryDTO> createCourse(@RequestBody CourseEntryCreationDTO courseEntryCreationDTO) {
        CourseEntryDTO courseEntryDTOCreated = courseEntryService.create(courseEntryCreationDTO);
        return ResponseEntity.created(URI.create("/api/v1/entries/" + courseEntryDTOCreated.id())).body(courseEntryDTOCreated);
    }

}
