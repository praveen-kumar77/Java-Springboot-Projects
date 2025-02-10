package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.EnrollmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepo extends JpaRepository<EnrollmentDetails, String> {

    @Query(value = "SELECT nextval('enrollment_details_seq')", nativeQuery = true)
    Long getNextEnrollmentIdSequence();
}
