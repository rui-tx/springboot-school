package com.ruitx.formation.dto;

public record CourseCreationDTO(
        String name,
        String description,
        int credits,
        String teacher,
        int maxStudents
) {}
