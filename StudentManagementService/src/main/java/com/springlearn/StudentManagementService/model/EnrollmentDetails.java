package com.springlearn.StudentManagementService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity
@Component
public class EnrollmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentId")
    @JsonIgnore
    private StudentDetails studentId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "courseId")
    @JsonIgnore
    private CourseDetails courseId;

    private LocalDate enrollmentDate;

    private EnrollmentStatus status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentDetails getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentDetails studentId) {
        this.studentId = studentId;
    }

    public CourseDetails getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseDetails courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}
