package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<TeacherDetails, String> {

    @Query(value = "SELECT nextval('teacher_details_seq')", nativeQuery = true)
    Long getNextTeacherIdSequence();
}
