package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.service.StudentService;
import com.springlearn.StudentManagementService.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PutMapping("update/{id}")
    public String updateStudentDetails(@PathVariable int id, @RequestBody StudentDetails studentDetails){
        return studentService.updateDetails(studentDetails);
    }

}
