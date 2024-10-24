package com.emplye_mangemnt_system.EMS.dto;

import com.emplye_mangemnt_system.EMS.utility.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private int empId;
    private String firstname;
    private String lastname;
    private String email;
    private String salary;
    private String address;
    private String mobile;
    private DepartmentDTO departmentid;
    private JobRoleDTO jobRoleid;
    private ManagersDTO managerId;
    private LocalDate joinDate;
    private Map<LeaveType, Double> leaveBalance;
}
