package com.springlearn.MappingTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int deptId;
    private String deptName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @JsonIgnore
    private List<EmployeeDetails> employeeDetails;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<EmployeeDetails> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<EmployeeDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}