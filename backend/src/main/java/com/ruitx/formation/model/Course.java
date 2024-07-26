package com.ruitx.formation.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "credits", nullable = false)
    private int credits;

    @Column(name = "max_students", nullable = false)
    private int maxStudents;

    @Column(name = "last_updated", columnDefinition = "TIMESTAMP")
    private Instant lastUpdated;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<CourseTeacher> CourseTeachers = new HashSet<>();

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Set<CourseTeacher> getCourseTeachers() {
        return CourseTeachers;
    }

    public void setCourseTeachers(Set<CourseTeacher> CourseTeachers) {
        this.CourseTeachers = CourseTeachers;
    }


}
