package com.ruitx.formation.service;

import com.ruitx.formation.dto.courseTeacher.CourseTeacherDTO;
import com.ruitx.formation.exceptions.courseTeacher.CourseTeacherNotFoundException;
import com.ruitx.formation.mapper.CourseTeacherMapper;
import com.ruitx.formation.model.CourseTeacher;
import com.ruitx.formation.repository.CourseTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ruitx.formation.exceptions.ErrorMessage.COURSE_TEACHER_GROUP_NOT_FOUND;

@Service
public class CourseTeacherService {
    private final CourseTeacherRepository courseTeacherRepository;

    @Autowired
    public CourseTeacherService(CourseTeacherRepository courseTeacherRepository) {
        this.courseTeacherRepository = courseTeacherRepository;
    }

    @Cacheable(value = "courseTeacherCache")
    public List<CourseTeacherDTO> getAll() {
        List<CourseTeacher> courseTeachers = (List<CourseTeacher>) courseTeacherRepository.findAll();
        List<CourseTeacherDTO> courseTeachersDTOs = new java.util.ArrayList<>();

        courseTeachers.forEach(courseTeacher -> courseTeachersDTOs.add(CourseTeacherMapper.toDTO(courseTeacher)));

        return courseTeachersDTOs;
    }

    public CourseTeacherDTO get(Long id) {
        CourseTeacher courseTeacher = courseTeacherRepository.findById(id).orElseThrow(() -> new CourseTeacherNotFoundException(COURSE_TEACHER_GROUP_NOT_FOUND));
        return CourseTeacherMapper.toDTO(courseTeacher);
    }

    // TODO: Implement create, update and delete
}
