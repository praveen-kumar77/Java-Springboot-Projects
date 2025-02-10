package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.*;
import com.springlearn.StudentManagementService.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/getDetails")
    @ResponseBody
    public ResponseEntity<StudentDetails> getDetails(@RequestParam String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getAllStudents")
    @ResponseBody
    public List<StudentDetails> getAllStudents(@RequestParam String designation, @RequestParam int semester){
        return studentService.getAllStudents(designation, semester);
    }

    @PutMapping("/studentUpdate")
    @ResponseBody
    public String updateStudentDetails(@ModelAttribute StudentDetails studentDetails) {
        studentService.updateDetails(studentDetails);
        return "{\"message\":\"Student details updated successfully\"}";
    }

    @PostMapping("/addStudent")
    @ResponseBody
    public ResponseEntity<StudentDetails> addStudentDetails(@ModelAttribute StudentDetails studentDetails) {
        return studentService.addStudentDetails(studentDetails);
    }

    @DeleteMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(@RequestParam String id){
        return studentService.deleteDetails(id);
    }

    @PostMapping("/addTeachersDetails")
    @ResponseBody
    public ResponseEntity<TeacherDetails> addDetails(@ModelAttribute TeacherDetails teacherDetails){
        return teacherService.addTeachersDetails(teacherDetails);
    }

    @PostMapping("/getTeachersDetails")
    @ResponseBody
    public ResponseEntity<TeacherDetails> getTeachersDetails(@RequestParam String id){
        return teacherService.getTeachersDetailsById(id);
    }

    @PutMapping("/updateTeachersDetails")
    @ResponseBody
    public ResponseEntity<TeacherDetails> updateDetails(@ModelAttribute TeacherDetails teacherDetails){
        return teacherService.updateTeachersDetails(teacherDetails);
    }

    @PostMapping("/addCourse")
    @ResponseBody
    public ResponseEntity<CourseDetails> addCourse(@ModelAttribute CourseDetails courseDetails){
        return courseService.addCourse(courseDetails);
    }

    @PostMapping("/setEnrollments")
    @ResponseBody
    public ResponseEntity<EnrollmentDetails> addStudentEnrollment(@RequestBody List<StudentDetails> studentsDetails,
                                                                  @RequestParam String course){
        return enrollmentService.addStudentEnrollment(studentsDetails, course);
    }

    @PostMapping("/assignCourses")
    @ResponseBody
    public ResponseEntity<TeacherDetails> setCourseToTeachers(@RequestBody List<String> courseIds,
                                                              @RequestParam String teacherId ){
        return teacherService.setCourses(courseIds, teacherId);
    }
//------------------------------------------------------------------------------------------------
    @GetMapping("/adminPage")
    public String updatedStudent(){
        return "adminPage";
    }


    @PostMapping("addCourseList")
    public List<CourseDetails> addCourseList(@RequestBody List<CourseDetails> courseDetailsList){
        return courseService.addCourseList(courseDetailsList);
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/getUserDetails/{id}")
    public UserCred getUserDetails(@PathVariable String  id) {
        return userService.getUserDetails(id);
    }


    @GetMapping("/home")
    public String admin(){
        return "adminPage";
    }

}
