package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.CourseDetails;
import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.model.TeacherDetails;
import com.springlearn.StudentManagementService.model.UserCred;
import com.springlearn.StudentManagementService.service.CourseService;
import com.springlearn.StudentManagementService.service.StudentService;
import com.springlearn.StudentManagementService.service.TeacherService;
import com.springlearn.StudentManagementService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/getDetails")
    @ResponseBody
    public ResponseEntity<StudentDetails> getDetails(@RequestParam int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getAllStudents")
    @ResponseBody
    public List<StudentDetails> getAllStudents(){
        return studentService.getAllStudents();
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
    public String deleteStudent(@RequestParam int id){
        return studentService.deleteDetails(id);
    }
//------------------------------------------------------------------------------------------------
    @GetMapping("/adminPage")
    public String updatedStudent(){
        return "adminPage";
    }

    @PostMapping("addCourse")
    public CourseDetails addCourse(@RequestBody CourseDetails courseDetails){
        return courseService.addCourse(courseDetails);
    }

    @PostMapping("addCourseList")
    public List<CourseDetails> addCourseList(@RequestBody List<CourseDetails> courseDetailsList){
        return courseService.addCourseList(courseDetailsList);
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/getUserDetails/{id}")
    public UserCred getUserDetails(@PathVariable int id) {
        return userService.getUserDetails(id);
    }


    @PostMapping("/addTeachersDetails")
    public TeacherDetails addDetails(@RequestBody TeacherDetails teacherDetails){
        return teacherService.addTeachersDetails(teacherDetails);
    }

    @PutMapping("/updateTeachersDetails")
    public TeacherDetails updateDetails(@RequestBody TeacherDetails teacherDetails){
        return teacherService.updateTeachersDetails(teacherDetails);
    }

    @GetMapping("/home")
    public String admin(){
        return "adminPage";
    }

}
