package com.ruitx.formation.dto.courseEntry;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CourseEntryDTO(
        @Schema(type = "long", example = "1") @NotBlank(message = "Id is required") Long id,
        @Schema(type = "long", example = "1000") @NotBlank(message = "Student Id is required") Long studentId,
        @Schema(type = "long", example = "20245") @NotBlank(message = "CourseId is required") Long courseId,
        @Schema(type = "string", example = "Student grade") Long grade,
        @Schema(type = "int", example = "2024") int dateRegistered,
        @Schema(type = "bool", example = "true") boolean isActive
) {
}