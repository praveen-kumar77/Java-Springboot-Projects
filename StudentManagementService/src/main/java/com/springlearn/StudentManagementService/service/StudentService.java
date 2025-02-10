package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.DeletedStudentDetails;
import com.springlearn.StudentManagementService.model.StudentDetails;
import com.springlearn.StudentManagementService.model.UserCred;
import com.springlearn.StudentManagementService.model.UserRole;
import com.springlearn.StudentManagementService.repo.DeletedStudentRepo;
import com.springlearn.StudentManagementService.repo.StudentRepo;
import com.springlearn.StudentManagementService.repo.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    DeletedStudentRepo deletedStudentRepo;

    @Autowired
    UserRepo userRepo;

    public String userNameGenerator(StudentDetails student){
        Random random = new Random();
        int randomNum = random.nextInt(999);
        String[] studentName = student.getStudentName().split(" ");
        String name = studentName[0] + "@" + randomNum;
        if(userRepo.findByUserName(name) != null)
        {
           return userNameGenerator(student);
        }
        return name;
    }

    public ResponseEntity<StudentDetails> addStudentDetails(StudentDetails studentDetails) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        UserCred userCred = new UserCred();
        studentDetails.setStatus(true);
        studentDetails.setCreatedAt(LocalDate.now());
        studentDetails.setUpdatedAt(null);
        studentDetails.setRegisterId(generateStudentId());
        studentRepo.save(studentDetails);

        userCred.setUserName(userNameGenerator(studentDetails));
        userCred.setPassword(encoder.encode(studentDetails.getDateOfBirth().toString()));
        userCred.setUserRole(UserRole.STUDENT);
        userRepo.save(userCred);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*public String addStudentsDetailsList(List<StudentDetails> studentDetailsList) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        for(StudentDetails studentDetails : studentDetailsList){
            UserCred userCred = new UserCred();

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
*/
    public String deleteDetails(String  id) {
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
        return "Student Detail Deleted";
    }


    public String getDetailsById(String id, HttpServletRequest request) {
        StudentDetails details = studentRepo.findById(id).get();
        request.setAttribute("student", details);
        return "studentPage";
    }



    public String updateDetails(StudentDetails studentDetails) {
        StudentDetails student = studentRepo.findByRegisterId(studentDetails.getRegisterId());
        studentDetails.setCreatedAt(student.getCreatedAt());
        studentDetails.setUpdatedAt(new Date());
        studentRepo.save(studentDetails);
        return "redirect:/adminPage";
    }

    public ResponseEntity<StudentDetails> getStudentById(String id) {
        StudentDetails student = studentRepo.findById(id).get();
            return new ResponseEntity<>(student, HttpStatus.OK);
    }

    public List<StudentDetails> getAllStudents() {
        return studentRepo.findByStatus();
    }

    public List<StudentDetails> getAllStudents(String designation, int semester) {

        if (Objects.equals(designation, "All") && semester != 0){
            return studentRepo.findByYearOfStudy(semester);
        } else if (!Objects.equals(designation, "All") && semester == 0) {
            return studentRepo.findByDesignation(designation);
        } else if (Objects.equals(designation, "All") && Objects.equals(semester, 0)){
            return studentRepo.findByStatus();
        } else{
            return studentRepo.findByDesignationAndYear(designation, semester);
        }

    }

    public String generateStudentId() {
        // Get the next value from the sequence
        Long sequenceValue = studentRepo.getNextStudentIdSequence();
        // Format: "STU" followed by a 5-digit number (e.g., STU25001)
        return String.format("SMSS25%03d", sequenceValue);
    }
}
