package com.springlearn.StudentDetails.controller;

import com.springlearn.StudentDetails.model.EmployeeDetails;
import com.springlearn.StudentDetails.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class empController {

    @Autowired
    EmployeeService service;

    @RequestMapping({"/index", "/"})
    public String home() {
        return "index";
    }

    @RequestMapping("/success")
    public String add(@ModelAttribute EmployeeDetails emp, Model model) {
        return service.addDetails(emp, model);
    }

    @RequestMapping("delete")
    public String softDelete(@RequestParam("id1") int id, Model model) {
        return service.delete(id, model);
    }

    @RequestMapping("show")
    public String getStudents(HttpServletRequest request) {
        return service.getDetails(request);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdatePage() {
        return "update";
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.POST)
    public String fetchStudent(@RequestParam("id") int id, Model model) {
        Optional<EmployeeDetails> empDetails = service.getStudentById(id);

        if (empDetails.isPresent()) {
            model.addAttribute("emp", empDetails.get());
        } else {
            model.addAttribute("error", "Student not found!");
        }
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute EmployeeDetails emp, Model model) {
        service.saveStudent(emp);
        model.addAttribute("success", "Student details updated successfully!");
        return "update";
    }
}
