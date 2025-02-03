package com.springlearn.StudentManagementService.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Component
public class UserCred {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int userId;

    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private StudentDetails studentId;


    public StudentDetails getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentDetails studentId) {
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

}
