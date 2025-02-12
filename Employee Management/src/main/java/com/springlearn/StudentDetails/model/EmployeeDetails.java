package com.springlearn.StudentDetails.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "employee")
@Component
@Data
public class EmployeeDetails {

    @Id
    public int id;
    public String name;
    public int age;
    public int salary;
    public boolean isActive;

}
