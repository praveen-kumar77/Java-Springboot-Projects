package com.springlearn.StudentManagementService.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class TeacherDetails {

    @Id
    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "teacher_name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_no")
    private long contactNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "joined_at")
    private LocalDate joinedAt;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "teachersId")
    private List<CourseDetails> courseHandling;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    }

    public List<CourseDetails> getCourseHandling() {
        return courseHandling;
    }

    public void setCourseHandling(List<CourseDetails> courseHandling) {
        this.courseHandling = courseHandling;
    }
}