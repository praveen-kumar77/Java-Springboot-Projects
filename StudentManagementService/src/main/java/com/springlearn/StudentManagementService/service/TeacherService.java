package com.springlearn.StudentManagementService.service;

import com.springlearn.StudentManagementService.model.TeacherDetails;
import com.springlearn.StudentManagementService.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    public TeacherDetails addTeachersDetails(TeacherDetails teacherDetails) {
        return teacherRepo.save(teacherDetails);
    }

    public TeacherDetails updateTeachersDetails(TeacherDetails teacherDetails) {
        return teacherDetails;
    }
}
