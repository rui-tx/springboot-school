package com.ruitx.formation.dto;

public record StudentDTO(
        Long id,
        String name,
        String email,
        String phone,
        int age,
        String gender
) {
}