package com.ruitx.formation.service;

import com.ruitx.formation.dto.StudentCreationDTO;
import com.ruitx.formation.dto.StudentDTO;
import com.ruitx.formation.exceptions.StudentNotFoundException;
import com.ruitx.formation.mapper.StudentMapper;
import com.ruitx.formation.model.Student;
import com.ruitx.formation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ruitx.formation.exceptions.ErrorMessage.STUDENT_NOT_FOUND;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        List<StudentDTO> studentsDTOs = new ArrayList<>();

        students.forEach(student -> studentsDTOs.add(StudentMapper.toDTO(student)));
        return studentsDTOs;
    }

    public StudentDTO get(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new StudentNotFoundException(STUDENT_NOT_FOUND);
        }
        return StudentMapper.toDTO(student.get());
    }

    public StudentDTO create(StudentCreationDTO studentCreationDTO) {
        Student newStudent = StudentMapper.fromCreationDTO(studentCreationDTO);
        Student savedStudent = studentRepository.save(newStudent);
        return StudentMapper.toDTO(savedStudent);
    }

    public StudentDTO update(Long id, StudentCreationDTO studentCreationDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(STUDENT_NOT_FOUND));
        student.setName(studentCreationDTO.name());
        student.setEmail(studentCreationDTO.email());
        student.setPhone(studentCreationDTO.phone());
        student.setAge(studentCreationDTO.age());
        student.setGender(studentCreationDTO.gender());
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toDTO(savedStudent);
    }

    public void delete(Long id) {
        Student studentToDelete = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(STUDENT_NOT_FOUND));
        studentRepository.delete(studentToDelete);
    }

}
