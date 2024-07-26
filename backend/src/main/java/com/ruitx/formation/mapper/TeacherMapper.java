package com.ruitx.formation.mapper;

import com.ruitx.formation.dto.teacher.TeacherCreationDTO;
import com.ruitx.formation.dto.teacher.TeacherDTO;
import com.ruitx.formation.model.Teacher;

public class TeacherMapper {

    public static TeacherDTO toDTO(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        return new TeacherDTO(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getPhone(),
                teacher.getAge(),
                teacher.getGender(),
                teacher.getDepartment()
        );
    }

    public static Teacher fromDTO(TeacherDTO teacherDTO) {
        if (teacherDTO == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.id());
        teacher.setName(teacherDTO.name());
        teacher.setEmail(teacherDTO.email());
        teacher.setPhone(teacherDTO.phone());
        teacher.setAge(teacherDTO.age());
        teacher.setGender(teacherDTO.gender());
        teacher.setDepartment(teacherDTO.department());
        return teacher;
    }

    public static Teacher fromCreationDTO(TeacherCreationDTO teacherCreationDTO) {
        if (teacherCreationDTO == null) {
            return null;
        }
        return createFromDTO(teacherCreationDTO, new Teacher());
    }

    public static Teacher createFromDTO(TeacherCreationDTO teacherCreationDTO, Teacher teacher) {
        if (teacherCreationDTO == null) {
            return null;
        }
        teacher.setName(teacherCreationDTO.name());
        teacher.setEmail(teacherCreationDTO.email());
        teacher.setPhone(teacherCreationDTO.phone());
        teacher.setAge(teacherCreationDTO.age());
        teacher.setGender(teacherCreationDTO.gender());
        teacher.setDepartment(teacherCreationDTO.department());

        return teacher;
    }
}