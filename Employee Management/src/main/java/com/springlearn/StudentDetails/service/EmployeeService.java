package com.springlearn.StudentDetails.service;

import com.springlearn.StudentDetails.model.EmployeeDetails;
import com.springlearn.StudentDetails.repo.EmployeeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Deletes an employee by setting their active status to false.
     *
     * @param id    The ID of the employee to delete.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    public String delete(int id, Model model) {
        Optional<EmployeeDetails> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            EmployeeDetails employee = employeeOptional.get();
            employee.setActive(false);
            employeeRepository.save(employee);
            System.out.println("Document archived successfully.");
        } else {
            System.out.println("Employee ID not found.");
        }

        model.addAttribute("message", "Deleted successfully.");
        return "index";
    }

    /**
     * Adds a new employee or updates an existing one.
     *
     * @param emp   The employee details to save.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    public String addDetails(@ModelAttribute EmployeeDetails emp, Model model) {
        emp.setActive(true);
        employeeRepository.save(emp);
        model.addAttribute("message", "Saved successfully!");
        return "index";
    }

    /**
     * Retrieves a list of active employees and sets it as a request attribute.
     *
     * @param request The HTTP request to set attributes to.
     * @return The name of the view to render.
     */
    public String getDetails(HttpServletRequest request) {
        List<EmployeeDetails> activeEmployees = employeeRepository.findByIsActiveTrue();
        request.setAttribute("emp", activeEmployees);
        return "show";
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return An Optional containing the employee details if found.
     */
    public Optional<EmployeeDetails> getStudentById(int id) {
        return employeeRepository.findById(id);
    }

    /**
     * Saves or updates an employee in the repository.
     *
     * @param emp The employee details to save.
     * @return The saved employee entity.
     */
    public EmployeeDetails saveStudent(EmployeeDetails emp) {
        return employeeRepository.save(emp);
    }
}
