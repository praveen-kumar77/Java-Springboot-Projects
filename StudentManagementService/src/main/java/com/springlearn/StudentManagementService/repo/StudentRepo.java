package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentDetails, Integer> {
}
