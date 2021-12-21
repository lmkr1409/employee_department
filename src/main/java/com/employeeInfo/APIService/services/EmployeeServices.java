package com.employeeInfo.APIService.services;

import com.employeeInfo.APIService.data.EmployeeRepository;
import com.employeeInfo.APIService.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {
    private final EmployeeRepository employeeRepository;

    public EmployeeServices(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public long getCostOfEmployee(Integer emp_id){
        Employee employee = employeeRepository.findByEmpId(emp_id);
        return employee.getCost();
    }

    public void createNewEmployee(Employee employee) {
        Optional<Employee> employeeByEmpEmail = employeeRepository.findEmployeeByEmpEmail(employee.getEmpEmail());
        if (employeeByEmpEmail.isPresent()) {
            throw new IllegalStateException("Email is already present in System");
        }
        // check if manager is passed from Body
        if (employee.getEmp_manager()  != null) {
            boolean exist = employeeRepository.existsById(Long.valueOf(employee.getEmp_manager()));
            if (exist) {
                // Check if the manager is actually a manager
                Employee isManager = employeeRepository.findByEmpId(employee.getEmp_manager());
                if (!isManager.getEmp_designation().equals("Manager")) {
                    throw new IllegalStateException("Employee can only report to a Manager, but not to " + isManager.getEmp_designation());
                }
                if (!isManager.getEmp_dept().equals(employee.getEmp_dept())) {
                    throw new IllegalStateException("Employee must belong to same department as manager i.e., " + isManager.getEmp_dept());
                }
            } else {
                throw new IllegalStateException("Provide Manager "+employee.getEmp_manager() + " doesn't exist in the System");
            }
        } else {
            // Employee must be a manager If he is not having any manager
            if(!employee.getEmp_designation().equals("Manager")){
                throw new IllegalStateException("Employee Must be a manager if he is not having any Manager");
            }
        }
        employeeRepository.save(employee);
    }

    public void deleteEmployee(long emp_id) {
        boolean exist = employeeRepository.existsById(emp_id);
        if(!exist){
            throw new IllegalStateException("Employee with id " + emp_id + " Doesnot exist");
        }
        employeeRepository.deleteById(emp_id);
    }


    public void updateEmployee(Employee employee) {
        boolean exist = employeeRepository.existsById(employee.getEmpId());
        if (!exist) {
            throw new IllegalStateException("Employee with Id " + employee.getEmpId() + " doesn't exist in Database");
        }
        employeeRepository.save(employee);
    }
}
