package com.ruitx.formation.service;

import com.ruitx.formation.dto.teacher.TeacherCreationDTO;
import com.ruitx.formation.dto.teacher.TeacherDTO;
import com.ruitx.formation.exceptions.teacher.TeacherNotFoundException;
import com.ruitx.formation.mapper.TeacherMapper;
import com.ruitx.formation.model.Teacher;
import com.ruitx.formation.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ruitx.formation.exceptions.ErrorMessage.TEACHER_NOT_FOUND;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Cacheable(value = "teacherCache")
    public List<TeacherDTO> getAll() {
        List<Teacher> teachers = (List<Teacher>) teacherRepository.findAll();
        List<TeacherDTO> teachersDTOs = new ArrayList<>();

        teachers.forEach(teacher -> teachersDTOs.add(TeacherMapper.toDTO(teacher)));
        return teachersDTOs;
    }

    public TeacherDTO get(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            throw new TeacherNotFoundException(TEACHER_NOT_FOUND);
        }
        return TeacherMapper.toDTO(teacher.get());
    }

    public TeacherDTO create(TeacherCreationDTO teacherCreationDTO) {
        Teacher newStudent = TeacherMapper.fromCreationDTO(teacherCreationDTO);
        Teacher savedTeacher = teacherRepository.save(newStudent);
        return TeacherMapper.toDTO(savedTeacher);
    }

    public TeacherDTO update(Long id, TeacherCreationDTO teacherCreationDTO) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(TEACHER_NOT_FOUND));
        teacher.setName(teacherCreationDTO.name());
        teacher.setEmail(teacherCreationDTO.email());
        teacher.setPhone(teacherCreationDTO.phone());
        teacher.setAge(teacherCreationDTO.age());
        teacher.setGender(teacherCreationDTO.gender());
        teacher.setDepartment(teacherCreationDTO.department());
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.toDTO(savedTeacher);
    }

    public void delete(Long id) {
        Teacher teacherToDelete = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(TEACHER_NOT_FOUND));
        teacherRepository.delete(teacherToDelete);
    }
}
