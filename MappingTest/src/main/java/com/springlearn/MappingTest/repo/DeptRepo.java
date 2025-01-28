package com.springlearn.MappingTest.repo;

import com.springlearn.MappingTest.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepo extends JpaRepository<Department, Integer> {

}