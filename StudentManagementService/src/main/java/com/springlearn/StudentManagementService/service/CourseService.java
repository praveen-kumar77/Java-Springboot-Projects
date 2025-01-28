package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.CourseDetails;
import com.springlearn.StudentManagementService.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepo courseRepo;


    public List<CourseDetails> getAllCourses() {
        return courseRepo.findAll();
    }

    public CourseDetails getCourseById(int id) {
        return courseRepo.findById(id).get();
    }

    public CourseDetails addCourse(CourseDetails courseDetails) {
        return courseRepo.save(courseDetails);
    }

    public CourseDetails update(CourseDetails courseDetails) {
        return courseRepo.save(courseDetails);
    }

    public String delete(int id) {
        courseRepo.deleteById(id);
        return "Record Deleted";
    }

    public String addAllCourse(List<CourseDetails> courseDetails) {
        courseRepo.saveAll(courseDetails);
        return "Added";
    }
}
