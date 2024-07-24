package com.ruitx.formation.mapper;

import com.ruitx.formation.dto.CourseEntryCreationDTO;
import com.ruitx.formation.dto.CourseEntryDTO;
import com.ruitx.formation.model.Course;
import com.ruitx.formation.model.CourseEntry;
import com.ruitx.formation.model.Student;

public class CourseEntryMapper {

    public static CourseEntryDTO toDTO(CourseEntry courseEntry) {
        if (courseEntry == null) {
            return null;
        }
        return new CourseEntryDTO(
                courseEntry.getId(),
                courseEntry.getStudent().getId(),
                courseEntry.getCourse().getId(),
                courseEntry.getGrade(),
                courseEntry.getDateRegistered(),
                courseEntry.isActive()
        );
    }

    public static CourseEntry fromDTO(CourseEntryDTO courseEntryDTO, Student student, Course course) {
        if (courseEntryDTO == null) {
            return null;
        }
        CourseEntry courseEntry = new CourseEntry();
        courseEntry.setId(courseEntryDTO.id());
        courseEntry.setStudent(student);
        courseEntry.setCourse(course);
        courseEntry.setGrade(courseEntryDTO.grade());
        courseEntry.setDateRegistered(courseEntryDTO.dateRegistered());
        courseEntry.setActive(courseEntryDTO.isActive());
        return courseEntry;
    }

    public static CourseEntry fromCreationDTO(CourseEntryCreationDTO courseEntryCreationDTO,
                                              Student student,
                                              Course course) {
        if (courseEntryCreationDTO == null) {
            return null;
        }
        CourseEntry courseEntry = new CourseEntry();
        courseEntry.setStudent(student);
        courseEntry.setCourse(course);
        courseEntry.setGrade(courseEntryCreationDTO.grade());
        courseEntry.setDateRegistered(courseEntryCreationDTO.dateRegistered());
        courseEntry.setActive(courseEntryCreationDTO.isActive());
        return courseEntry;
    }
}