package com.springlearn.StudentManagementService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class CourseDetails {

    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    @JsonIgnore
    private TeacherDetails teachersId;

    @OneToMany(mappedBy = "courId")
    private List<EnrollmentDetails> courseEnrollmentsList;

    public TeacherDetails getTeachersId() {
        return teachersId;
    }

    public void setTeachersId(TeacherDetails teacherId) {
        this.teachersId = teacherId;
    }

    public List<EnrollmentDetails> getCourseEnrollmentsList() {
        return courseEnrollmentsList;
    }

    public void setCourseEnrollmentsList(List<EnrollmentDetails> courseEnrollmentsList) {
        this.courseEnrollmentsList = courseEnrollmentsList;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
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

    public String  getCourseId() {
        return courseId;
    }

    public void setCourseId(String  courseId) {
        this.courseId = courseId;
    }

}
