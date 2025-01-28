package com.springlearn.MappingTest.controller;

import com.springlearn.MappingTest.model.Department;
import com.springlearn.MappingTest.model.Employee;
import com.springlearn.MappingTest.model.EmployeeDetails;
import com.springlearn.MappingTest.service.MappingService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private MappingService service;

    public MainController(MappingService service) {
        this.service = service;
    }

    @GetMapping("home")
    public String home(){
        return "home";
    }
    @PostMapping("empPost")
    public Employee empDetails(@RequestBody Employee employee){
        return service.saveDetails(employee);
    }

    @PostMapping("deptPost")
    public Department foreignAdd(@RequestBody Department department){
        return service.saveDeptDetails(department);
    }

    @PostMapping("empDetailsPost")
    public EmployeeDetails thirdAdd(@RequestBody EmployeeDetails employeeDetails){
       return service.saveRetriveDetails(employeeDetails);
    }

    @GetMapping("emp/{id}")
    public EmployeeDetails getDetails(@PathVariable int id){
        return service.getDetailsById(id);
    }
}
