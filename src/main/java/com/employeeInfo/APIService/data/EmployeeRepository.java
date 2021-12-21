package com.employeeInfo.APIService.data;

import com.employeeInfo.APIService.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmpId(long emp_id);
    Optional<Employee> findEmployeeByEmpEmail(String email);
}
