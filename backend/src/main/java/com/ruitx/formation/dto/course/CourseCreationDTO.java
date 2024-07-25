package com.ruitx.formation.dto.course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CourseCreationDTO(
        @Schema(type = "string", example = "Mathematics 101") @NotBlank(message = "Course name is required") String name,
        @Schema(type = "string", example = "Entry mathematics for first year students") String description,
        @Schema(type = "int", example = "10") int credits,
        @Schema(type = "string", example = "Mr. Rogers") String teacher,
        @Schema(type = "int", example = "20") int maxStudents
) {
}
