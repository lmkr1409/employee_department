package com.employeeInfo.APIService.controllers;


import com.employeeInfo.APIService.models.Department;
import com.employeeInfo.APIService.models.Employee;
import com.employeeInfo.APIService.services.DepartmentServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices){
        this.departmentServices=departmentServices;
    }

    @GetMapping("/dept_list")
    public List<Department> getAllDepartments(){
        return departmentServices.getAllDepartments();
    }

    @GetMapping("/dept_details")
    public Department getDepartmentDetails(@RequestParam(value = "dept_id", defaultValue = "0") Integer dept_id){
        if (dept_id == 0){
            return null;
        }
        return departmentServices.getDepartmentDetails(dept_id);
    }

    @GetMapping("/dept_cost")
    public Integer getDepartmentCost(@RequestParam(value = "dept_id", defaultValue = "0") Integer dept_id){
        if (dept_id == 0){
            return null;
        }
        return departmentServices.getCostOfDepartment(dept_id);
    }
}
