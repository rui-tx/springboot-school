package com.ruitx.formation.mapper;

import com.ruitx.formation.dto.courseTeacher.CourseTeacherCreationDTO;
import com.ruitx.formation.dto.courseTeacher.CourseTeacherDTO;
import com.ruitx.formation.model.Course;
import com.ruitx.formation.model.CourseTeacher;
import com.ruitx.formation.model.Teacher;

public class CourseTeacherMapper {

    public static CourseTeacherDTO toDTO(CourseTeacher courseTeacher) {
        if (courseTeacher == null) {
            return null;
        }
        return new CourseTeacherDTO(
                courseTeacher.getId(),
                courseTeacher.getCourse().getId(),
                courseTeacher.getTeacher().getId(),
                courseTeacher.getDateRegistered()
        );
    }

    public static CourseTeacher fromDTO(CourseTeacherDTO courseTeacherDTO, Course course, Teacher teacher) {
        if (courseTeacherDTO == null) {
            return null;
        }
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setId(courseTeacherDTO.id());
        courseTeacher.setCourse(course);
        courseTeacher.setTeacher(teacher);
        courseTeacher.setDateRegistered(courseTeacherDTO.dateRegistered());
        return courseTeacher;
    }

    public static CourseTeacher fromCreationDTO(CourseTeacherCreationDTO courseTeacherCreationDTO, Course course, Teacher teacher) {
        if (courseTeacherCreationDTO == null) {
            return null;
        }
        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse(course);
        courseTeacher.setTeacher(teacher);
        courseTeacher.setDateRegistered(courseTeacherCreationDTO.dateRegistered());
        return courseTeacher;
    }
}