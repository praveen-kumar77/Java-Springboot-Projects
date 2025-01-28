package com.springlearn.MappingTest.repo;

import com.springlearn.MappingTest.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDetailsRepo extends JpaRepository<EmployeeDetails, Integer> {
}
