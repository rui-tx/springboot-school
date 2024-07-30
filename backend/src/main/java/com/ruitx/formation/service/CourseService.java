package com.ruitx.formation.service;

import com.ruitx.formation.dto.course.CourseCreationDTO;
import com.ruitx.formation.dto.course.CourseDTO;
import com.ruitx.formation.exceptions.course.CourseNotFoundException;
import com.ruitx.formation.mapper.CourseMapper;
import com.ruitx.formation.model.Course;
import com.ruitx.formation.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ruitx.formation.exceptions.ErrorMessage.COURSE_NOT_FOUND;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Get all courses
     *
     * @return List of courses
     */
    @Cacheable(value = "courseCache", unless = "#result == null")
    public List<CourseDTO> getAll() {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        List<CourseDTO> coursesDTOs = new ArrayList<>();

        courses.forEach(course -> coursesDTOs.add(CourseMapper.toDTO(course)));

        return coursesDTOs;
    }

    /**
     * Get a course by id
     *
     * @param id Course id
     * @return Course default object
     */
    @Cacheable(value = "courseCacheId", key = "#id", unless = "#result == null")
    public CourseDTO get(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isEmpty()) {
            throw new CourseNotFoundException(COURSE_NOT_FOUND);
        }

        return CourseMapper.toDTO(course.get());
    }

    /**
     * Create a course
     *
     * @param courseCreationDTO Course creation DTO
     * @return Course default object
     */
    public CourseDTO create(CourseCreationDTO courseCreationDTO) {
        Course newCourse = CourseMapper.fromCreationDTO(courseCreationDTO);
        Course savedCourse = courseRepository.save(newCourse);
        return CourseMapper.toDTO(savedCourse);
    }

    /**
     * Delete a course
     *
     * @param id Course id
     */
    public void delete(Long id) {
        Course courseToDelete = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND));
        courseRepository.delete(courseToDelete);
    }
}
