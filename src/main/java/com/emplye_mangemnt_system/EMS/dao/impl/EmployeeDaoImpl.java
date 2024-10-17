package com.emplye_mangemnt_system.EMS.dao.impl;

import com.emplye_mangemnt_system.EMS.dao.EmployeeDao;
import com.emplye_mangemnt_system.EMS.pojo.Department;
import com.emplye_mangemnt_system.EMS.pojo.Employee;
import com.emplye_mangemnt_system.EMS.pojo.JobRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

//    public void createTable(){
//        String create="CREATE TABLE EMP(empId INT,FIRSTNAME VARCHAR(30),LASTNAME VARCHAR(30), EMAIL VARCHAR(30)," +
//                "SALARY VARCHAR(30),ADDRESS VARCHAR(30),MOBILE VARCHAR(15)," +
//                "FOREIGN KEY (department_id) REFERENCES departments(id),\n" +
//                "    FOREIGN KEY (job_role_id) REFERENCES job_roles(id)\n)";
//        jdbcTemplate.update(create);
//    }

//    @Override
//    public void batchInsert(List<Employee> employees) {
//        System.out.println("from batch insert method");
//        String insert="INSERT INTO EMP(EMPID,FIRSTNAME,LASTNAME,EMAIL,SALARY,ADDRESS,MOBILE,departmentId,jobRoleId) VALUES(?,?,?,?,?,?,?,?,?)";
//        jdbcTemplate.batchUpdate(insert,employees,employees.size(),
//                (ps, employee) -> {
//                    System.out.println("start excuting");
//                    ps.setInt(1, employee.getEmpId());
//                    System.out.println("1");
//                    ps.setString(2, employee.getFirstName());
//                    System.out.println("2");
//                    ps.setString(3,employee.getLastName());
//                    System.out.println("3");
//                    ps.setString(4,employee.getEmail());
//                    System.out.println("4");
//                    ps.setString(5,employee.getSalary());
//                    System.out.println("5");
//                    ps.setString(6, employee.getAddress());
//                    System.out.println("6");
//                    ps.setString(7, employee.getMobile());
//                    System.out.println("7");
//                    ps.setInt(8, employee.getDepartmentId());
//                    System.out.println("8");
//                    ps.setInt(9, employee.getJobRoleId());
//                    System.out.println("9");
//                });

//        System.out.println("successfully inserted list of emplys");
//    }

    @Override
    public void batchInsert(List<Employee> employees) {
        System.out.println("from batch insert method");

        // Correct SQL with departmentid and jobroleid
        String insert = "INSERT INTO emp(EMPID, FIRSTNAME, LASTNAME, EMAIL, SALARY, ADDRESS, MOBILE, departmentid, jobroleid) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(insert, employees, employees.size(),
                (ps, employee) -> {
                    // Set the parameters from the Employee object
                    ps.setInt(1, employee.getEmpId());
                    ps.setString(2, employee.getFirstName());
                    ps.setString(3, employee.getLastName());
                    ps.setString(4, employee.getEmail());
                    ps.setString(5, employee.getSalary());
                    ps.setString(6, employee.getAddress());
                    ps.setString(7, employee.getMobile());
                    ps.setLong(8, employee.getDepartmentid().getId());  // departmentid should be of type BIGINT
                    ps.setLong(9, employee.getJobRoleid().getId());      // jobroleid should be of type BIGINT
                }
        );

        System.out.println("Successfully inserted list of employees");
    }


    @Override
    public int save(Employee employee) {
        System.out.println("start excuting save()");
        String insert = "INSERT INTO emp(EMPID, FIRSTNAME, LASTNAME, EMAIL, SALARY, ADDRESS, MOBILE, departmentid, jobroleid) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("sql query || "+insert);
        return jdbcTemplate.update(insert,employee.getEmpId(),employee.getFirstName(),
                employee.getLastName(),employee.getEmail(),employee.getSalary(),
                employee.getAddress(),employee.getMobile(),employee.getDepartmentid(),
                employee.getJobRoleid());

    }


    @Override
    public Employee updateEmp(Employee employee) {
        System.out.println("start updating from Dao");
        String sql ="update emp set empname=?,designation=? where empid=?";
        int updt=jdbcTemplate.update(sql,employee.getFirstName(),employee.getEmpId());
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