package com.emplye_mangemnt_system.EMS.dao.impl;

import com.emplye_mangemnt_system.EMS.dao.EmployeeDao;
import com.emplye_mangemnt_system.EMS.pojo.Department;
import com.emplye_mangemnt_system.EMS.pojo.Employee;
import com.emplye_mangemnt_system.EMS.pojo.JobRole;
import com.emplye_mangemnt_system.EMS.pojo.Managers;
import com.emplye_mangemnt_system.EMS.utility.LeaveType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                    ps.setString(2, employee.getFirstname());
                    ps.setString(3, employee.getLastname());
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
        int count = 0;
        System.out.println("start excuting save()");
        String insert = "INSERT INTO emp(EMPID, FIRSTNAME, LASTNAME, EMAIL, SALARY, ADDRESS, MOBILE, departmentid, jobroleid,managerId,joinDate,leaveBalance) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        System.out.println("sql query || " + insert);

        ObjectMapper mapper = new ObjectMapper();
        String leaveBalanceJson;
        try {
            leaveBalanceJson = mapper.writeValueAsString(employee.getLeaveBalance());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        count = jdbcTemplate.update(insert, employee.getEmpId(), employee.getFirstname(),
                employee.getLastname(), employee.getEmail(), employee.getSalary(),
                employee.getAddress(), employee.getMobile(), employee.getDepartmentid().getId(),
                employee.getJobRoleid().getId(), employee.getManagerId().getManagerId(), employee.getJoinDate(), leaveBalanceJson);
        return count;
    }

//    private Long managerId;
//    private LocalDate joinDate;
//    private Map<LeaveType, Double> leaveBalance;


    @Override
    public Employee updateEmp(Employee employee) {
        System.out.println("start updating from Dao");
        String sql ="update emp set empname=?,designation=? where empid=?";
        int updt=jdbcTemplate.update(sql,employee.getFirstname(),employee.getEmpId());
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
    public Employee findEmployeById(int rollno) {

        String find = "SELECT empid, firstname, lastname, email, salary, address, mobile,\n"+
                "d.id AS departmentid, d.name AS department_name, \n"+
                "jr.id AS jobroleid, jr.title AS jobrole_title, jr.description AS jobrole_description,\n"+
                "m.managerId AS managerId,m.employeeId AS employeeId,\n"+
                "m.managementLevel AS managementLevel, m.department AS department,\n"+
        "m.maxDirectReports AS maxDirectReports, m.departmentBudget AS departmentBudget,\n"+
        "m.effectiveFrom as effectiveFrom, m.effectiveTo as effectiveTo, m.createdAt as createdAt," +
                " m.updatedAt AS updatedAt,m.isActive AS isActive, m.reportingManagerId AS reportingManagerId," +
                "emp.joinDate, emp.leaveBalance FROM emp\n"+
        "JOIN departments d ON emp.departmentid = d.id\n"+
        "JOIN job_roles jr ON emp.jobroleid = jr.id\n"+
        "JOIN managers m on emp.managerId=m.managerId\n"+
        "WHERE empid = ?";


        // Query to get the employee details by empid
        return jdbcTemplate.queryForObject(find, new Object[]{rollno}, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setEmpId(rs.getInt("empid"));
            employee.setFirstname(rs.getString("firstname"));
            employee.setLastname(rs.getString("lastname"));
            employee.setEmail(rs.getString("email"));
            employee.setSalary(rs.getString("salary"));
            employee.setAddress(rs.getString("address"));
            employee.setMobile(rs.getString("mobile"));
            employee.setDepartmentid(new Department(rs.getLong("departmentid")));  // Assuming departmentid is a foreign key
            employee.setJobRoleid(new JobRole(rs.getLong("jobroleid")));            // Assuming jobroleid is a foreign key
            employee.setManagerId(new Managers(rs.getLong("managerId")));
            employee.setJoinDate(rs.getDate("joinDate").toLocalDate());
//            employee.setLeaveBalance((Map<LeaveType, Double>) rs.getObject("leaveBalance"));
            if (rs.getObject("leaveBalance") != null) {
                ObjectMapper mapper = new ObjectMapper();
                Map<LeaveType, Double> leaveBalanceMap = null;
                try {
                    leaveBalanceMap = mapper.readValue(rs.getObject("leaveBalance").toString(), new TypeReference<Map<LeaveType, Double>>() {});
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                employee.setLeaveBalance(leaveBalanceMap);
            }


            // Mapping department details
            Department department = new Department();
            department.setId(rs.getLong("departmentid"));
            department.setName(rs.getString("department_name"));
            employee.setDepartmentid(department);

            // Mapping job role details
            JobRole jobRole = new JobRole();
            jobRole.setId(rs.getLong("jobroleid"));
            jobRole.setTitle(rs.getString(getJobroleTitle()));
            jobRole.setDescription(rs.getString("jobrole_description"));
            employee.setJobRoleid(jobRole);

            //mapping managers details
            Managers managers = new Managers();
            managers.setManagerId(rs.getLong("managerId"));
            managers.setEmployeeId(rs.getLong("employeeId"));
            managers.setManagementLevel(rs.getString("managementLevel"));
            managers.setDepartment(rs.getString("department"));
            managers.setMaxDirectReports(rs.getInt("maxDirectReports"));
            managers.setEffectiveFrom(rs.getDate("effectiveFrom").toLocalDate());
            managers.setEffectiveTo(rs.getDate("effectiveTo").toLocalDate());
            managers.setCreatedAt(rs.getDate("createdAt").toLocalDate().atStartOfDay());
            managers.setUpdatedAt(rs.getDate("updatedAt").toLocalDate().atStartOfDay());
            managers.setActive(rs.getBoolean("isActive"));
            managers.setReportingManagerId(rs.getLong("reportingManagerId"));
            return employee;
        });
    }

    private static String getJobroleTitle() {
        return "jobrole_title";
    }

}
//private int empId;
//private String empName;
//private String designation;