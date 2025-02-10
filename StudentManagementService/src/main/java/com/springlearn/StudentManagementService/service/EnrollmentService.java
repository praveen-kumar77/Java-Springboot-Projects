package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.CourseDetails;
import com.springlearn.StudentManagementService.model.EnrollmentDetails;
import com.springlearn.StudentManagementService.model.EnrollmentStatus;
import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.repo.CourseRepo;
import com.springlearn.StudentManagementService.repo.EnrollmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    public ResponseEntity<EnrollmentDetails> addStudentEnrollment(List<StudentDetails> studentDetails,
                                                                  String courseId) {
        CourseDetails course = courseRepo.findById(courseId).get();

        for(StudentDetails student : studentDetails){
            EnrollmentDetails enrollment = new EnrollmentDetails();

            enrollment.setStudId(student);
            enrollment.setCourId(course);
            enrollment.setEnrolledAt(LocalDate.now());
            enrollment.setEnrollmentId(generateEnrollId());
            enrollment.setEnrollmentStatus(EnrollmentStatus.ACTIVE);

            student.getStudentEnrollmentsList().add(enrollment);
            course.getCourseEnrollmentsList().add(enrollment);

            enrollmentRepo.save(enrollment);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public String generateEnrollId() {
        Long sequenceValue = enrollmentRepo.getNextEnrollmentIdSequence();
        return String.format("ENROLL25%03d", sequenceValue);
    }
}

