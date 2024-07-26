package com.ruitx.formation.repository;

import com.ruitx.formation.model.Class;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {
}