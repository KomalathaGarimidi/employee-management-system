package com.emplye_mangemnt_system.EMS.pojo;

import com.emplye_mangemnt_system.EMS.utility.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private int empId;
    private String firstname;
    private String lastname;
    private String email;
    private String salary;
    private String address;
    private String mobile;
    private  Department departmentid;
    private JobRole jobRoleid;
    private Managers managerId;
    private LocalDate joinDate;
    private Map<LeaveType, Double> leaveBalance;


}
