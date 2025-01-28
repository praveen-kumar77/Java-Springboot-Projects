package com.springlearn.MappingTest.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity
@Component
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int empDetailsId;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee employee;

    public int getEmpDetailsId() {
        return empDetailsId;
    }

    public void setEmpDetailsId(int empDetailsId) {
        this.empDetailsId = empDetailsId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

