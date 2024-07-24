package com.ruitx.formation.dto;

public record CourseEntryCreationDTO(
        Long studentId,
        Long courseId,
        Long grade,
        int dateRegistered,
        boolean isActive
) {
}
