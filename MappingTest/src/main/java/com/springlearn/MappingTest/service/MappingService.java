package com.springlearn.MappingTest.service;

import com.springlearn.MappingTest.model.Department;
import com.springlearn.MappingTest.model.Employee;
import com.springlearn.MappingTest.model.EmployeeDetails;
import com.springlearn.MappingTest.repo.DeptRepo;
import com.springlearn.MappingTest.repo.EmpDetailsRepo;
import com.springlearn.MappingTest.repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    @Autowired
    EmpRepo empRepo;

    @Autowired
    DeptRepo deptRepo;

    @Autowired
    EmpDetailsRepo empDetailRepo;

    public EmployeeDetails saveRetriveDetails(EmployeeDetails employeeDetails) {

        Department department = deptRepo.findById(employeeDetails.getDepartment().getDeptId()).get();
        Employee employee = empRepo.findById(employeeDetails.getEmployee().getEmpId()).get();

        employeeDetails.setDepartment(department);
        employeeDetails.setEmployee(employee);

        return empDetailRepo.save(employeeDetails);
    }

    public Employee saveDetails(Employee employee) {
        return empRepo.save(employee);
    }

    public Department saveDeptDetails(Department department) {
        return deptRepo.save(department);
    }

    public EmployeeDetails getDetailsById(int id) {
        return empDetailRepo.findById(id).get();
    }
}
