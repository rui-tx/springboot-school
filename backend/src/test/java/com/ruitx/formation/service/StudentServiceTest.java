package com.ruitx.formation.service;

import com.ruitx.formation.dto.StudentDTO;
import com.ruitx.formation.exceptions.StudentNotFoundException;
import com.ruitx.formation.model.Student;
import com.ruitx.formation.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void get_shouldSucceed() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John");
        student.setEmail("john@gmail.com");
        student.setPhone("+123456789");
        student.setAge(20);
        student.setGender("M");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentDTO studentDTO = studentService.get(1L);
        assertEquals(1L, studentDTO.id());
    }

    @Test
    void get_shouldNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(StudentNotFoundException.class, () -> {
            StudentDTO studentDTO = studentService.get(1L);
        });
    }

    @Test
    void create() {
    }
}