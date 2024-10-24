package com.emplye_mangemnt_system.EMS.service.impl;

import com.emplye_mangemnt_system.EMS.dao.EmployeeDao;
import com.emplye_mangemnt_system.EMS.pojo.Employee;
import com.emplye_mangemnt_system.EMS.service.EmployeeService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    Logger logger ;
    @Autowired
    EmployeeDao employeeDao;


    @Override
    public Employee saveEmployee(Employee employee) {
        // First check if employee exists
        Employee existingEmployee = findEmployeById(employee.getEmpId());

        System.out.println("existingEmployee || "+existingEmployee);

        // Logical error: Your condition is reversed
        if (existingEmployee != null) {
            // If employee exists, throw exception or handle accordingly
            throw new RuntimeException("Employee with ID " + employee.getEmpId() + " already exists");
        }

        System.out.println("Saving new employee");
        int savedRows = employeeDao.save(employee);

        if (savedRows == 0) {
            throw new RuntimeException("Failed to save employee");
        }

        return employee;
    }

    @Override
    public Employee findEmployeById(int rollno) {

        Employee employee= employeeDao.findEmployeById(rollno);
        System.out.println("employee");
        return employee;
    }


    @Override
    public void batchInsert(List<Employee> employees) {
//        employeeDao.createTable();
        employeeDao.batchInsert(employees);
    }

    @Override
    public Employee updateEmp(Employee employee) {
        System.out.println("updateemp from service");
        employeeDao.updateEmp(employee);
        return employee;
    }

    @Override
    public boolean deleteEmp(int rollno) {
        System.out.println("delet() from service");
        return employeeDao.deleteEmp(rollno);
    }
    @Override
    public List<Map<String, Object>> readEmp(){
        return employeeDao.readEmp();
    }
}

