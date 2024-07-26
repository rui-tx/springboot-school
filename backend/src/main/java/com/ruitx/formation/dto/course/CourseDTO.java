package com.ruitx.formation.dto.course;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
        @Schema(type = "long", example = "20245") @NotBlank(message = "Course Id is required") Long id,
        @Schema(type = "string", example = "Mathematics 101") @NotBlank(message = "Course name is required") String name,
        @Schema(type = "string", example = "Entry mathematics for first year students") String description,
        @Schema(type = "int", example = "10") int credits,
        @Schema(type = "int", example = "20") int maxStudents
) {
}
