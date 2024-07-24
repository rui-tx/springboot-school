package com.ruitx.formation.repository;

import com.ruitx.formation.model.Course;
import com.ruitx.formation.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
