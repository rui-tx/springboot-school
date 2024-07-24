package com.ruitx.formation.dto;

public record CourseDTO(
        Long id,
        String name,
        String description,
        int credits,
        String teacher,
        int maxStudents
) {}
