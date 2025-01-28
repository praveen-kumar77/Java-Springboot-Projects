package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.CourseDetails;
import com.springlearn.StudentManagementService.model.EnrollmentDetails;
import com.springlearn.StudentManagementService.repo.EnrollmentRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepo enrollmentRepo;


    public String enrollDetails(EnrollmentDetails enrollmentDetails) {
        enrollmentRepo.save(enrollmentDetails);
        return "Enrolled";
    }
}
