package com.ruitx.formation.service;

import com.ruitx.formation.dto.courseEntry.CourseEntryCreationDTO;
import com.ruitx.formation.dto.courseEntry.CourseEntryDTO;
import com.ruitx.formation.exceptions.course.CourseNotFoundException;
import com.ruitx.formation.exceptions.courseEntry.CourseEntryAlreadyExistsException;
import com.ruitx.formation.exceptions.courseEntry.CourseEntryNotFoundException;
import com.ruitx.formation.exceptions.student.StudentNotFoundException;
import com.ruitx.formation.mapper.CourseEntryMapper;
import com.ruitx.formation.model.Course;
import com.ruitx.formation.model.CourseEntry;
import com.ruitx.formation.model.Student;
import com.ruitx.formation.repository.CourseEntryRepository;
import com.ruitx.formation.repository.CourseRepository;
import com.ruitx.formation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ruitx.formation.exceptions.ErrorMessage.*;

@Service
public class CourseEntryService {
    private final CourseEntryRepository courseEntryRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseEntryService(CourseEntryRepository courseEntryRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.courseEntryRepository = courseEntryRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Cacheable(value = "entriesCache", unless = "#result == null")
    public List<CourseEntryDTO> getAll() {
        List<CourseEntry> entries = (List<CourseEntry>) courseEntryRepository.findAll();
        List<CourseEntryDTO> entriesDTOs = new ArrayList<>();

        entries.forEach(entry -> entriesDTOs.add(CourseEntryMapper.toDTO(entry)));

        return entriesDTOs;
    }

    @Cacheable(value = "entriesCacheId", key = "#id", unless = "#result == null")
    public CourseEntryDTO get(Long id) {
        Optional<CourseEntry> entry = courseEntryRepository.findById(id);

        if (entry.isEmpty()) {
            throw new CourseEntryNotFoundException(COURSE_ENTRY_NOT_FOUND);
        }

        return CourseEntryMapper.toDTO(entry.get());
    }

    public CourseEntryDTO create(CourseEntryCreationDTO entryCreationDTO) {

        Student student = studentRepository
                .findById(entryCreationDTO.studentId())
                .orElseThrow(() -> new StudentNotFoundException(STUDENT_NOT_FOUND));

        Course course = courseRepository
                .findById(entryCreationDTO.courseId())
                .orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND));

        CourseEntry newEntry = CourseEntryMapper.fromCreationDTO(entryCreationDTO, student, course);

        if (checkIfStudentIsEnrolled(newEntry)) {
            throw new CourseEntryAlreadyExistsException(COURSE_ENTRY_ALREADY_EXISTS);
        }

        CourseEntry savedEntry = courseEntryRepository.save(newEntry);
        return CourseEntryMapper.toDTO(savedEntry);
    }

    private boolean checkIfStudentIsEnrolled(CourseEntry courseEntry) {
        return courseEntry.getStudent().getStudentCourses()
                .stream()
                .anyMatch(studentCourse -> studentCourse.getCourse().getId().equals(courseEntry.getCourse().getId()));
    }
}