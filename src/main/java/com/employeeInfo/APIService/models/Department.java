package com.employeeInfo.APIService.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Department")
public class Department {
    @Id
    @Column(name="dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deptId;

    @Column(name = "dept_name")
    private String dept_name;

    public Department(){

    }

    public Department(long id, String name){
        this.deptId = id;
        this.dept_name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Employee.class)
    @JoinColumn(name = "emp_dept")
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        ArrayList<Employee> newList = new ArrayList<>();
        for (Employee employee: employeeList){
            if (employee.getEmp_manager() == null) {
                newList.add(employee);
            }
        }
        return newList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
