package com.ruitx.formation.mapper;

import com.ruitx.formation.dto.CourseCreationDTO;
import com.ruitx.formation.dto.CourseDTO;
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
                course.getTeacher(),
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
        course.setTeacher(courseDTO.teacher());
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
        course.setTeacher(courseCreationDTO.teacher());
        course.setMaxStudents(courseCreationDTO.maxStudents());

        return course;
    }
}
