package com.ruitx.formation.controller;

import com.ruitx.formation.dto.classPkg.ClassDTO;
import com.ruitx.formation.exceptions.ErrorMessage;
import com.ruitx.formation.service.ClassService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruitx.formation.config.Constants.CLASS_MAPPING;

@Tag(name = "Classes", description = "Classes API")
@RestController
@RequestMapping(CLASS_MAPPING)
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @Operation(summary = "List all classes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ClassDTO.class))))
    })
    @GetMapping("/")
    public ResponseEntity<List<ClassDTO>> getAll() {
        List<ClassDTO> classDTOList = classService.getAll();
        return ResponseEntity.ok(classDTOList);
    }

    @Operation(summary = "Get a class by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClassDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = ErrorMessage.CLASS_NOT_FOUND,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(example = "{\"message\":\"Class not found\"}")))

    })
    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable Long id) {
        ClassDTO classDTO = classService.get(id);
        return ResponseEntity.ok(classDTO);
    }

    //TODO: create other endpoints
}
