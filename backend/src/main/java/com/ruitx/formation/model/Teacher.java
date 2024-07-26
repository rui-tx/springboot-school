package com.ruitx.formation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "last_updated", columnDefinition = "TIMESTAMP")
    private Instant lastUpdated;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Set<CourseTeacher> getCourseTeachers() {
        return CourseTeachers;
    }

    public void setCourseTeachers(Set<CourseTeacher> CourseTeachers) {
        this.CourseTeachers = CourseTeachers;
    }

}
