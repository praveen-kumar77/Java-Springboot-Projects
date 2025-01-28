package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.EnrollmentDetails;
import com.springlearn.StudentManagementService.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping("enroll")
    public String enroll(@RequestBody EnrollmentDetails enrollmentDetails){
        return enrollmentService.enrollDetails(enrollmentDetails);
    }
}