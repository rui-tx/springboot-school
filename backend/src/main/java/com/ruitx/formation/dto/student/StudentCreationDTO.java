package com.ruitx.formation.dto.student;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record StudentCreationDTO(
        @Schema(type = "string", example = "Sofia Martins") @NotBlank(message = "Name is required") String name,
        @Schema(type = "string", example = "sofia.martins@company.com") @NotBlank(message = "Email is required") String email,
        @Schema(type = "int", example = "912345678") String phone,
        @Schema(type = "int", example = "28") int age,
        @Schema(type = "string", example = "F") String gender
) {
}