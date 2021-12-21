package com.employeeInfo.APIService.data;

import com.employeeInfo.APIService.models.Department;
import com.employeeInfo.APIService.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDeptId(long dept_id);
}
