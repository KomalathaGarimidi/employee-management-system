package com.emplye_mangemnt_system.EMS.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private int empId;
    private String firstName;
    private String lastName;
    private String email;
    private String salary;
    private String address;
    private String mobile;
    private  Department departmentid;
    private JobRole jobRoleid;

}
