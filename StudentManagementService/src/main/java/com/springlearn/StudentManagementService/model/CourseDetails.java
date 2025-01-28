package com.springlearn.StudentManagementService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int courseId;
    private String courseName;
    private String description;
    private int credit;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "courseId")
    @JsonIgnore
    private List<EnrollmentDetails> enrollmentDetails;

    @ManyToMany(mappedBy = "courseDetails", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentDetails> studentDetails;

    public List<StudentDetails> getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(List<StudentDetails> studentDetails) {
        this.studentDetails = studentDetails;
    }

    public int getCourseId() {
        return courseId;
    }

    public List<EnrollmentDetails> getEnrollmentDetails() {
        return enrollmentDetails;
    }

    public void setEnrollmentDetails(List<EnrollmentDetails> enrollmentDetails) {
        this.enrollmentDetails = enrollmentDetails;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }


}
