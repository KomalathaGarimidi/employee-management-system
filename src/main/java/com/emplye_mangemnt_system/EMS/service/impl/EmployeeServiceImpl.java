package com.emplye_mangemnt_system.EMS.service.impl;

import com.emplye_mangemnt_system.EMS.dao.EmployeeDao;
import com.emplye_mangemnt_system.EMS.pojo.Employee;
import com.emplye_mangemnt_system.EMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;


    @Override
    public Employee saveEmployee(Employee employee) {

        employeeDao.save(employee);
        return employee;
    }

    @Override
    public boolean findEmployeById(int rollno) {
        return employeeDao.findEmployeById(rollno);

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

