package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.CourseDetails;
import com.springlearn.StudentManagementService.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepo courseRepo;

    public CourseDetails addCourse(CourseDetails courseDetails) {
        return courseRepo.save(courseDetails);
    }

    public List<CourseDetails> addCourseList(List<CourseDetails> courseDetailsList) {
        for(CourseDetails s: courseDetailsList){
            s.setCreatedAt(new Date(System.currentTimeMillis()));
            s.setUpdatedAt(null);

            courseRepo.save(s);
        }
        return courseRepo.saveAll(courseDetailsList);
    }
}
