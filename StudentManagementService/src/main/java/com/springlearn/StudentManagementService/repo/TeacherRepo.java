package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<TeacherDetails, Integer> {
}
