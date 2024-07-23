package com.ruitx.formation.dto;

public record StudentCreationDTO(
        String name,
        String email,
        String phone,
        int age,
        String gender
) {
}