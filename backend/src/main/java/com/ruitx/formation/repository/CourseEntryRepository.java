package com.ruitx.formation.repository;

import com.ruitx.formation.model.CourseEntry;
import com.ruitx.formation.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEntryRepository extends CrudRepository<CourseEntry, Long> {
}
