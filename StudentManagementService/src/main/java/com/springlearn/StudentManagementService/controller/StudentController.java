package com.springlearn.StudentManagementService.controller;

import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/admin/add")
    public String saveDetails(@RequestBody StudentDetails studentDetails){
        return studentService.saveDetails(studentDetails);
    }

    @GetMapping("/admin/allStudents")
    public List<StudentDetails> allStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public StudentDetails getStudent(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/admin/update")
    public StudentDetails update(@RequestBody StudentDetails student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("admin/delete/{id}")
    public String delete(@PathVariable int id){
        return studentService.deleteStudent(id);
    }

}
