package com.springlearn.StudentManagementService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class EnrollmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int enrollmentId;

    @ManyToOne
    @JsonIgnore
    private StudentDetails studId;

    @ManyToOne
    @JsonIgnore
    private CourseDetails courId;


}
