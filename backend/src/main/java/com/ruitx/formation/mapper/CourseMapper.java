package com.ruitx.formation.mapper;

import com.ruitx.formation.dto.course.CourseCreationDTO;
import com.ruitx.formation.dto.course.CourseDTO;
import com.ruitx.formation.model.Course;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getCredits(),
                course.getMaxStudents()
        );
    }

    public static Course fromDTO(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        course.setId(courseDTO.id());
        course.setName(courseDTO.name());
        course.setDescription(courseDTO.description());
        course.setCredits(courseDTO.credits());
        course.setMaxStudents(courseDTO.maxStudents());
        return course;
    }

    public static Course fromCreationDTO(CourseCreationDTO courseCreationDTO) {
        if (courseCreationDTO == null) {
            return null;
        }
        return createFromDTO(courseCreationDTO, new Course());
    }

    public static Course createFromDTO(CourseCreationDTO courseCreationDTO, Course course) {
        if (courseCreationDTO == null) {
            return null;
        }
        course.setName(courseCreationDTO.name());
        course.setDescription(courseCreationDTO.description());
        course.setCredits(courseCreationDTO.credits());
        course.setMaxStudents(courseCreationDTO.maxStudents());

        return course;
    }
}
