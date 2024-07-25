package com.ruitx.formation.controller;

import com.ruitx.formation.dto.course.CourseCreationDTO;
import com.ruitx.formation.dto.course.CourseDTO;
import com.ruitx.formation.exceptions.ErrorMessage;
import com.ruitx.formation.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Courses controller
 */
@Tag(name = "Courses", description = "Courses API")
@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * List all courses
     *
     * @return List of courses
     */
    @Operation(summary = "List all courses")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class))))
    })
    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> courseDTOList = courseService.getAll();
        return ResponseEntity.ok(courseDTOList);
    }

    /**
     * Get a course by id
     *
     * @param id Course id
     * @return Course
     */
    @Operation(summary = "Get a course by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CourseDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = ErrorMessage.COURSE_NOT_FOUND,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(example = "{\"message\":\"Course not found\"}")))

    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO courseDTO = courseService.get(id);
        return ResponseEntity.ok(courseDTO);
    }

    /**
     * Create a course
     *
     * @param courseCreationDTO Course creation DTO
     * @return Course
     */
    @Operation(summary = "Create a course")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CourseDTO.class)))
    })
    @PostMapping("/")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseCreationDTO courseCreationDTO) {
        CourseDTO courseDTOCreated = courseService.create(courseCreationDTO);
        return ResponseEntity.created(URI.create("/api/v1/courses/" + courseDTOCreated.id())).body(courseDTOCreated);
    }

    /**
     * Delete a course
     *
     * @param id
     * @return
     */
    @Operation(summary = "Delete a course")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content"),
            @ApiResponse(
                    responseCode = "404",
                    description = ErrorMessage.COURSE_NOT_FOUND)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
