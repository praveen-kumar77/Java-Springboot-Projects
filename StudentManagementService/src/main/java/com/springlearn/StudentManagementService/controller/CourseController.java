package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.CourseDetails;
import com.springlearn.StudentManagementService.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/allCourse")
    public List<CourseDetails> allCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDetails getCourse(@PathVariable int id){
        return courseService.getCourseById(id);
    }

    @PostMapping("/addCourse")
    public CourseDetails addCourse(@RequestBody CourseDetails courseDetails){
        return courseService.addCourse(courseDetails);
    }

    @PutMapping("/update")
    public CourseDetails updateCourse(@RequestBody CourseDetails courseDetails){
        return courseService.update(courseDetails);
    }

    @DeleteMapping("delete/{id}")
    public String deleteCourse(@PathVariable int id){
        return courseService.delete(id);
    }

    @PostMapping("addAllCourse")
    public String addAllCourse(@RequestBody List<CourseDetails> courseDetails){
        return courseService.addAllCourse(courseDetails);
    }
}