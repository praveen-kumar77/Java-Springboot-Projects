package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<CourseDetails, String> {

    @Query(value = "SELECT nextval('course_details_seq')", nativeQuery = true)
    Long getNextCourseIdSequence();

}