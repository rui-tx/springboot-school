package com.ruitx.formation.mapper;

import com.ruitx.formation.dto.StudentCreationDTO;
import com.ruitx.formation.dto.StudentDTO;
import com.ruitx.formation.model.Student;

public class StudentMapper {

    public static StudentDTO toDTO(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getAge(),
                student.getGender()
        );
    }

    public static Student fromDTO(StudentCreationDTO createStudentDTO) {
        if (createStudentDTO == null) {
            return null;
        }
        Student student = new Student();
        student.setName(createStudentDTO.name());
        student.setEmail(createStudentDTO.email());
        student.setPhone(createStudentDTO.phone());
        student.setAge(createStudentDTO.age());
        student.setGender(createStudentDTO.gender());
        return student;
    }

    public static Student fromCreationDTO(StudentCreationDTO createStudentDTO) {
        if (createStudentDTO == null) {
            return null;
        }

        return createFromDTO(createStudentDTO, new Student());
    }

    public static Student createFromDTO(StudentCreationDTO createStudentDTO, Student student) {
        if (createStudentDTO == null) {
            return null;
        }
        student.setName(createStudentDTO.name());
        student.setEmail(createStudentDTO.email());
        student.setPhone(createStudentDTO.phone());
        student.setAge(createStudentDTO.age());
        student.setGender(createStudentDTO.gender());

        return student;

    }
}