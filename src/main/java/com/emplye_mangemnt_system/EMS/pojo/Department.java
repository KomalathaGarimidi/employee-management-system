package com.emplye_mangemnt_system.EMS.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long id;
    private String name;

//    public Department(long departmentid) {
//    }


//     Constructor with ID
    public Department(long id) {
        this.id = id;
    }
}

