package com.springlearn.StudentDetails.repo;

import com.springlearn.StudentDetails.model.EmployeeDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeDetails, Integer > {

    List<EmployeeDetails> findByIsActiveTrue();
}
