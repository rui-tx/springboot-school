package com.ruitx.formation.service;

import com.ruitx.formation.dto.StudentCreationDTO;
import com.ruitx.formation.dto.StudentDTO;
import com.ruitx.formation.mapper.StudentMapper;
import com.ruitx.formation.model.Student;
import com.ruitx.formation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    //private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        //this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }


    public StudentDTO get(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return StudentMapper.toDTO(student.get());
    }

    public StudentDTO create(StudentCreationDTO studentCreationDTO) {
        Student newStudent = StudentMapper.fromCreationDTO(studentCreationDTO);
        Student savedStudent = studentRepository.save(newStudent);
        return StudentMapper.toDTO(savedStudent);
    }


}
