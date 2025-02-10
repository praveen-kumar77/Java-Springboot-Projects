package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.*;
import com.springlearn.StudentManagementService.repo.CourseRepo;
import com.springlearn.StudentManagementService.repo.TeacherRepo;
import com.springlearn.StudentManagementService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    UserRepo userRepo;

    public String userNameGenerator(TeacherDetails teacher){
        Random random = new Random();
        int randomNum = random.nextInt(999);
        String[] studentName = teacher.getName().split(" ");
        String name = studentName[0] + "@" + randomNum;
        if(userRepo.findByUserName(name) != null)
        {
            return userNameGenerator(teacher);
        }
        return name;
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<TeacherDetails> addTeachersDetails(TeacherDetails teacherDetails) {

        UserCred userCred = new UserCred();

        userCred.setUserName(userNameGenerator(teacherDetails));
        userCred.setPassword(encoder.encode(teacherDetails.getDateOfBirth().toString()));
        userCred.setUserRole(UserRole.TEACHER);
        userRepo.save(userCred);

        teacherDetails.setTeacherId(generateTeacherId());
        teacherRepo.save(teacherDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<TeacherDetails> updateTeachersDetails(TeacherDetails teacherDetails) {
        teacherRepo.save(teacherDetails);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<TeacherDetails> getTeachersDetailsById(String id) {
        TeacherDetails teacher = teacherRepo.findById(id).get();
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    public ResponseEntity<TeacherDetails> setCourses(List<String> courseIds, String teacherId) {

        TeacherDetails teacher = teacherRepo.findById(teacherId).get();

        for(String courseId : courseIds){
            CourseDetails course = courseRepo.findById(courseId).get();

            teacher.getCourseHandling().add(course);
            course.setTeachersId(teacher);
            course.setUpdatedAt(new Date(System.currentTimeMillis()));

            courseRepo.save(course);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String generateTeacherId() {
        Long sequenceValue = teacherRepo.getNextTeacherIdSequence();
        return String.format("SMST25%03d", sequenceValue);
    }
}
