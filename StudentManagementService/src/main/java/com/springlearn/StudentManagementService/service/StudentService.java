package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public String saveDetails(StudentDetails studentDetails) {
        studentRepo.save(studentDetails);
        return "Saved Successfully";
    }

    public List<StudentDetails> getAllStudents() {
        return studentRepo.findAll();
    }

    public StudentDetails getStudentById(int id) {
        return studentRepo.findById(id).get();
    }

    public StudentDetails updateStudent(StudentDetails student) {
        return studentRepo.save(student);
    }

    public String deleteStudent(int id) {
        studentRepo.deleteById(id);
        return "Record Deleted";
    }
}
