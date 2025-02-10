package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.DeletedStudentDetails;
import com.springlearn.StudentManagementService.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedStudentRepo extends JpaRepository<DeletedStudentDetails, String> {

}
