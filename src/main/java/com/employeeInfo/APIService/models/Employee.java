package com.employeeInfo.APIService.models;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @Column(name="emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @Column(name = "emp_name")
    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String emp_name;

    @Column(name = "emp_dob")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date emp_dob;

    @Email
    @Column(name = "emp_email")
    private String empEmail;

    @Column(name = "emp_salary")
    private long emp_salary;

    @Column(name = "emp_manager")
    private Integer emp_manager;

    @Column(name = "emp_dept")
    private Integer emp_dept;

    @Column(name = "emp_designation")
    private String emp_designation;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Employee.class)
    @JoinColumn(name = "emp_manager")
    private List<Employee> listOfAssociates;

    @Transient
    private long cost;

    public long getCost() {
        long cost = getEmp_salary();

        for(Employee eachEmp: listOfAssociates){
            cost = cost + eachEmp.getCost();
        }
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Date getEmp_dob() {
        return emp_dob;
    }

    public void setEmp_dob(Date emp_dob) {
        this.emp_dob = emp_dob;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public long getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(long emp_salary) {
        this.emp_salary = emp_salary;
    }

    public Integer getEmp_manager() {
        return emp_manager;
    }

    public void setEmp_manager(Integer emp_manager) {
        this.emp_manager = emp_manager;
    }

    public Integer getEmp_dept() {
        return emp_dept;
    }

    public void setEmp_dept(Integer emp_dept) {
        this.emp_dept = emp_dept;
    }

    public String getEmp_designation() {
        return emp_designation;
    }

    public void setEmp_designation(String emp_designation) {
        this.emp_designation = emp_designation;
    }

    public List<Employee> getListOfAssociates() {
        return listOfAssociates;
    }

    public void setListOfAssociates(List<Employee> listOfAssociates) {
        this.listOfAssociates = listOfAssociates;
    }
}
