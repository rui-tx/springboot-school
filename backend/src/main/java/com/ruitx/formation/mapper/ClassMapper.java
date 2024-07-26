package com.ruitx.formation.mapper;

import com.ruitx.formation.dto.classPkg.ClassCreationDTO;
import com.ruitx.formation.dto.classPkg.ClassDTO;
import com.ruitx.formation.model.Class;

public class ClassMapper {

    public static ClassDTO toDTO(Class classP) {
        if (classP == null) {
            return null;
        }
        return new ClassDTO(
                classP.getId(),
                classP.getName(),
                classP.getDateRegistered()
        );
    }

    public static Class fromDTO(ClassDTO classDTO) {
        if (classDTO == null) {
            return null;
        }
        Class classP = new Class();
        classP.setId(classDTO.id());
        classP.setName(classDTO.name());
        classP.setDateRegistered(classDTO.dateRegistered());
        return classP;
    }

    public static Class fromCreationDTO(ClassCreationDTO classCreationDTO) {
        if (classCreationDTO == null) {
            return null;
        }
        return createFromDTO(classCreationDTO, new Class());
    }

    public static Class createFromDTO(ClassCreationDTO classCreationDTO, Class classP) {
        if (classCreationDTO == null) {
            return null;
        }
        classP.setName(classCreationDTO.name());
        classP.setDateRegistered(classCreationDTO.dateRegistered());

        return classP;
    }
}