package com.ruitx.formation.service;

import com.ruitx.formation.dto.classPkg.ClassDTO;
import com.ruitx.formation.exceptions.classExp.ClassNotFoundException;
import com.ruitx.formation.mapper.ClassMapper;
import com.ruitx.formation.model.Class;
import com.ruitx.formation.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ruitx.formation.exceptions.ErrorMessage.CLASS_NOT_FOUND;

@Service
public class ClassService {
    private final ClassRepository classRepository;

    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Cacheable(value = "classCache", unless = "#result == null")
    public List<ClassDTO> getAll() {
        List<Class> classes = (List<Class>) classRepository.findAll();
        List<ClassDTO> classesDTOs = new ArrayList<>();

        classes.forEach(c -> classesDTOs.add(ClassMapper.toDTO(c)));

        return classesDTOs;
    }

    @Cacheable(value = "classCacheId", key = "#id", unless = "#result == null")
    public ClassDTO get(Long id) {
        Optional<Class> c = classRepository.findById(id);

        if (c.isEmpty()) {
            throw new ClassNotFoundException(CLASS_NOT_FOUND);
        }

        return ClassMapper.toDTO(c.get());
    }
}
