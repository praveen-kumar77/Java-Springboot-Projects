package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<StudentDetails, Integer> {

    @Query("Select s from StudentDetails s where s.status = true ")
    List<StudentDetails> findByStatus();
}
