package com.springlearn.StudentManagementService.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class TeacherDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int TeacherId;

    private String name;
    private String address;
    private long contactNo;
    private String emailId;
    private String qualification;
    private LocalDate joinedAt;

    @OneToMany(mappedBy = "teacherId")
    private List<CourseDetails> courseHandling;

    public int getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(int teacherId) {
        TeacherId = teacherId;
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
