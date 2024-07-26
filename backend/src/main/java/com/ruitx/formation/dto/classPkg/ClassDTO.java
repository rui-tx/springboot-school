package com.ruitx.formation.dto.classPkg;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record ClassDTO(
        @Schema(type = "long", example = "20245") @NotBlank(message = "Class Id is required") Long id,
        @Schema(type = "string", example = "101-A") @NotBlank(message = "Class name is required") String name,
        @Schema(type = "date", example = "2024-01-01") LocalDateTime dateRegistered
) {
}
