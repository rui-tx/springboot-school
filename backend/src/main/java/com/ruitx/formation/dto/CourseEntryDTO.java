package com.ruitx.formation.dto;

public record CourseEntryDTO(
        Long id,
        Long studentId,
        Long courseId,
        Long grade,
        int dateRegistered,
        boolean isActive
) {
}
