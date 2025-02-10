package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.CourseDetails;
import com.springlearn.StudentManagementService.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepo courseRepo;

    public ResponseEntity<CourseDetails> addCourse(CourseDetails courseDetails) {
        courseDetails.setCreatedAt(LocalDate.now());
        courseDetails.setUpdatedAt(null);
        courseDetails.setCourseId(generateCourseId());
        courseRepo.save(courseDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<CourseDetails> addCourseList(List<CourseDetails> courseDetailsList) {
        for(CourseDetails s: courseDetailsList){
            s.setCreatedAt(LocalDate.now());
            s.setUpdatedAt(null);
            courseRepo.save(s);
        }
        return courseRepo.saveAll(courseDetailsList);
    }

    public String generateCourseId() {
        Long sequenceValue = courseRepo.getNextCourseIdSequence();
        return String.format("COURSE%03d", sequenceValue);
    }
}