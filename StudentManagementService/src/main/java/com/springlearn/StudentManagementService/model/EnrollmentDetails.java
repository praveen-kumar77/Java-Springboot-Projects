package com.springlearn.StudentManagementService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class EnrollmentDetails {

    @Id
    @Column(name = "enrollment_id")
    private String enrollmentId;

    @Column(name = "enroll_status")
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;

    @Column(name = "enrolled_at")
    private LocalDate enrolledAt;

    @ManyToOne
    @JsonIgnore
    private StudentDetails studId;

    @ManyToOne
    @JsonIgnore
    private CourseDetails courId;

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public LocalDate getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDate enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public StudentDetails getStudId() {
        return studId;
    }

    public void setStudId(StudentDetails studId) {
        this.studId = studId;
    }

    public CourseDetails getCourId() {
        return courId;
    }

    public void setCourId(CourseDetails courId) {
        this.courId = courId;
    }

}
