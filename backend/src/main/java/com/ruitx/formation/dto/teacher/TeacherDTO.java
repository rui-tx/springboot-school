package com.ruitx.formation.dto.teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TeacherDTO(
        @Schema(type = "long", example = "1000") @NotBlank(message = "Id is required") Long id,
        @Schema(type = "string", example = "Bill Gates") @NotBlank(message = "Name is required") String name,
        @Schema(type = "string", example = "bill.g@company.com") @NotBlank(message = "Email is required") String email,
        @Schema(type = "int", example = "912345678") String phone,
        @Schema(type = "int", example = "28") int age,
        @Schema(type = "string", example = "F") String gender,
        @Schema(type = "string", example = "Computer Science") String department
) {
}
