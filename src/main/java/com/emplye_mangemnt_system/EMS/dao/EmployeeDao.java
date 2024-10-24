package com.emplye_mangemnt_system.EMS.dao;

import com.emplye_mangemnt_system.EMS.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {



    int save(Employee employee);
    Employee findEmployeById(int rollno);
//    void createTable();
    void batchInsert(List<Employee> employee);
    Employee updateEmp(Employee employee);
    boolean deleteEmp(int rollno);
    List<Map<String, Object>> readEmp();

}
