package com.springlearn.StudentManagementService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int courseId;
    private String courseName;
    private String description;
    private int duration;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JsonIgnore
    private TeacherDetails teacherId;

    @OneToMany(mappedBy = "courId")
    private List<EnrollmentDetails> courseEnrollmentsList;

    public TeacherDetails getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(TeacherDetails teacherId) {
        this.teacherId = teacherId;
    }

    public List<EnrollmentDetails> getCourseEnrollmentsList() {
        return courseEnrollmentsList;
    }

    public void setCourseEnrollmentsList(List<EnrollmentDetails> courseEnrollmentsList) {
        this.courseEnrollmentsList = courseEnrollmentsList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
