package com.ruitx.formation.dto.courseEntry;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CourseEntryDTO(
        @Schema(type = "long", example = "1") @NotBlank(message = "Id is required") Long id,
        @Schema(type = "long", example = "1000") @NotBlank(message = "Student Id is required") Long studentId,
        @Schema(type = "long", example = "20245") @NotBlank(message = "CourseId is required") Long courseId,
        @Schema(type = "float", example = "19.5") Float grade,
        @Schema(type = "date", example = "2024-01-01") @NotBlank(message = "Date is required") LocalDate dateRegistered,
        @Schema(type = "bool", example = "true") @NotBlank(message = "Course should be active or not") boolean isActive
) {
}