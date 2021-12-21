package com.employeeInfo.APIService.controllers;

import com.employeeInfo.APIService.models.Employee;
import com.employeeInfo.APIService.services.EmployeeServices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping("/emp_details")
    public List<Employee> getAllEmployees() {
        return employeeServices.getAllEmployees();
    }

    @GetMapping("/emp_cost")
    public Integer getEmpCost(@RequestParam(value = "emp_id", defaultValue = "0") Integer emp_id) {
        if (emp_id == 0) {
            return null;
        }
        return Math.toIntExact(employeeServices.getCostOfEmployee(emp_id));
    }

    @PostMapping("/emp_details")
    public String createEmployee(@RequestBody Employee employee) {
        try {
            employeeServices.createNewEmployee(employee);
            return "Employee " + employee.getEmp_name() + " created";
        } catch (IllegalStateException e) {
            return "Failed to create employee:\n" + e.toString();
        }
    }

    @DeleteMapping("/emp_details")
    public String deleteEmployee(@RequestParam(value = "emp_id", defaultValue = "0") Integer emp_id) {
        try {
            employeeServices.deleteEmployee(emp_id);
            return "Employee with id " + emp_id + " is deleted";
        } catch (IllegalStateException e) {
            return "Failed to delete the Employee:\n" + e.toString();
        }
    }

    @PutMapping("/emp_details")
    public String updateEmployee(@RequestBody Employee employee) {
        try {
            employeeServices.updateEmployee(employee);
            return "Employee with id " + employee.getEmpId() + " is updated";
        } catch (IllegalStateException e) {
            return "Failed to update the Employee:\n" + e.toString();
        }
    }
}
