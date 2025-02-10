package com.springlearn.StudentManagementService.repo;

import com.springlearn.StudentManagementService.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<StudentDetails, String> {

    StudentDetails findByRegisterId(String id);

    @Query("Select s from StudentDetails s where s.status = true ")
    List<StudentDetails> findByStatus();

    @Query("select s from StudentDetails s where s.designation = :designation")
    List<StudentDetails> findByDesignation(String designation);

    @Query("select s from StudentDetails s where s.yearOfStudy = :semester")
    List<StudentDetails> findByYearOfStudy(int semester);

    @Query("select s from StudentDetails s where s.designation = :designation and s.yearOfStudy = :semester")
    List<StudentDetails> findByDesignationAndYear(String designation, int semester);

    @Query(value = "SELECT nextval('student_details_seq')", nativeQuery = true)
    Long getNextStudentIdSequence();

}
