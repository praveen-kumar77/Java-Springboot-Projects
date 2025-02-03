package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.DeletedStudentDetails;
import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.model.UserCred;
import com.springlearn.StudentManagementService.model.UserRole;
import com.springlearn.StudentManagementService.repo.DeletedStudentRepo;
import com.springlearn.StudentManagementService.repo.StudentRepo;
import com.springlearn.StudentManagementService.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    DeletedStudentRepo deletedStudentRepo;

    @Autowired
    UserRepo userRepo;


    public ResponseEntity<StudentDetails> addStudentDetails(StudentDetails studentDetails) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        UserCred userCred = new UserCred();
        studentDetails.setStatus(true);
        studentDetails.setCreatedAt(LocalDate.now());
        studentDetails.setUpdatedAt(null);
        studentRepo.save(studentDetails);

        userCred.setStudentId(studentDetails);
        userCred.setUserName(studentDetails.getStudentName());
        userCred.setPassword(encoder.encode(studentDetails.getDateOfBirth().toString()));
        userCred.setUserRole(UserRole.STUDENT);
        userRepo.save(userCred);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public String addStudentsDetailsList(List<StudentDetails> studentDetailsList) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        for(StudentDetails studentDetails : studentDetailsList){
            UserCred userCred = new UserCred();

            userCred.setStudentId(studentDetails);
            userCred.setUserName(studentDetails.getStudentName());
            userCred.setPassword(encoder.encode(studentDetails.getDateOfBirth().toString()));
            userCred.setUserRole(UserRole.STUDENT);
            userRepo.save(userCred);

            studentDetails.setStatus(true);
            studentDetails.setCreatedAt(LocalDate.now());
            studentDetails.setUpdatedAt(null);
            studentRepo.save(studentDetails);
        }
        return "Details saved";
    }

    public String deleteDetails(int id) {
        StudentDetails student = studentRepo.findById(id).get();
        student.setStatus(false);

        DeletedStudentDetails deletedStudentDetails = new DeletedStudentDetails();

        deletedStudentDetails.setAddress(student.getAddress());
        deletedStudentDetails.setCourseDuration(student.getCourseDuration());
        deletedStudentDetails.setStudentName(student.getStudentName());
        deletedStudentDetails.setDesignation(student.getDesignation());
        deletedStudentDetails.setPhone(student.getPhone());
        deletedStudentDetails.setMailId(student.getMailId());
        deletedStudentDetails.setRegisterId(student.getRegisterId());
        deletedStudentDetails.setStatus(student.isStatus());
        deletedStudentDetails.setDateOfBirth(student.getDateOfBirth());
        deletedStudentDetails.setCreatedAt(student.getCreatedAt());
        deletedStudentDetails.setUpdatedAt(new Date(System.currentTimeMillis()));
        deletedStudentDetails.setYearOfAdmission(student.getYearOfAdmission());
        deletedStudentDetails.setYearOfStudy(student.getYearOfStudy());

        deletedStudentRepo.save(deletedStudentDetails);
        studentRepo.deleteById(student.getRegisterId());
        return "Student Detail Deleted";
    }


    public String getDetailsById(int id, HttpServletRequest request) {
        StudentDetails details = studentRepo.findById(id).get();
        request.setAttribute("student", details);
        return "studentPage";
    }



    public String updateDetails(StudentDetails studentDetails) {
        StudentDetails student = studentRepo.findById(studentDetails.getRegisterId()).get();
        studentDetails.setCreatedAt(student.getCreatedAt());
        studentDetails.setUpdatedAt(new Date());
        studentRepo.save(studentDetails);
        return "redirect:/adminPage";
    }

    public ResponseEntity<StudentDetails> getStudentById(int id) {
        StudentDetails student = studentRepo.findById(id).get();
            return new ResponseEntity<>(student, HttpStatus.OK);
    }

    public List<StudentDetails> getAllStudents() {
        return studentRepo.findByStatus();
    }
}
