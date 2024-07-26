package com.ruitx.formation.repository;

import com.ruitx.formation.model.CourseTeacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTeacherRepository extends CrudRepository<CourseTeacher, Long> {
}