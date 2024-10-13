package com.emplye_mangemnt_system.EMS.service;

import com.emplye_mangemnt_system.EMS.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);
    public boolean findEmployeById(int rollno);
    public void batchInsert(List<Employee> employees);
    public Employee updateEmp(Employee employee);
    boolean deleteEmp(int rollno);
    List<Map<String, Object>> readEmp();
}
