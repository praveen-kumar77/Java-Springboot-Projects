package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/std")
    public String home(){
        return "studentPage";
    }

    @GetMapping("getDetails")
    public String getStudentDetails(){
        return "studentPage";
    }

    @PostMapping("getDetails")
    public String getDetails(@RequestParam("id") int id, HttpServletRequest request){
        return studentService.getDetailsById(id,request );
    }



}
