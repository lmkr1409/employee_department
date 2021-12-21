package com.employeeInfo.APIService.services;

import com.employeeInfo.APIService.data.DepartmentRepository;
import com.employeeInfo.APIService.data.EmployeeRepository;
import com.employeeInfo.APIService.models.Department;
import com.employeeInfo.APIService.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServices {
    private final DepartmentRepository departmentRepository;


    public DepartmentServices(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentDetails(Integer dept_id) {
        return departmentRepository.findByDeptId(dept_id);
    }
    
    public Integer getCostOfDepartment(Integer dept_id){
        Department dept = departmentRepository.findByDeptId(dept_id);
        int total = 0;
        for (Employee emp : dept.getEmployeeList()){
            total+=emp.getCost();
        }
        return total;
    }

}