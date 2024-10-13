package com.emplye_mangemnt_system.EMS.dao.impl;

import com.emplye_mangemnt_system.EMS.dao.EmployeeDao;
import com.emplye_mangemnt_system.EMS.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createTable(){
        String create="CREATE TABLE EMP(EMPID INT,EMPNAME VARCHAR(30),DESIGNATION VARCHAR(30))";
        jdbcTemplate.update(create);
    }

    @Override
    public void batchInsert(List<Employee> employees) {
        System.out.println("from batch insert method");
        String insert="INSERT INTO EMP(EMPID,EMPNAME,DESIGNATION) VALUES(?,?,?)";
        jdbcTemplate.batchUpdate(insert,employees,employees.size(),
                (ps, employee) -> {
                    ps.setInt(1, employee.getEmpId());
                    ps.setString(2, employee.getEmpName());
                    ps.setString(3, employee.getDesignation());
                });

        System.out.println("successfully inserted list of emplys");
    }

    @Override
    public Employee updateEmp(Employee employee) {
        System.out.println("start updating from Dao");
        String sql ="update emp set empname=?,designation=? where empid=?";
        int updt=jdbcTemplate.update(sql,employee.getEmpName(),employee.getDesignation(),employee.getEmpId());
        return employee;
    }

    @Override
    public boolean deleteEmp(int rollno) {
        System.out.println("start delete from dao");
        String sql = "delete from emp where empid =?";
        int updt = jdbcTemplate.update(sql,rollno);
        System.out.println("updt from delete || "+updt);
        return updt>0;

    }

    @Override
    public List<Map<String, Object>> readEmp() {
        String sql="select * from emp";
        List<Map<String, Object>> student = jdbcTemplate.queryForList(sql);
        return student;
    }


    @Override
    public int save(Employee employee) {
        String insert="INSERT INTO EMP(EMPID,EMPNAME,DESIGNATION) VALUES(?,?,?)";
        return jdbcTemplate.update(insert,employee.getEmpId(),employee.getEmpName(),employee.getDesignation());

    }

    @Override
    public boolean findEmployeById(int rollno) {
        String find="select empid from emp where empid=?";
        // Query to check if the employee exists (returns 1 if exists, 0 otherwise)
        int count = jdbcTemplate.queryForObject(find, new Object[]{rollno}, Integer.class);

        // Return true if employee exists, false otherwise
        return count > 0;
    }

}
//private int empId;
//private String empName;
//private String designation;