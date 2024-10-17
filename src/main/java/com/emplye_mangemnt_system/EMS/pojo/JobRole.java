package com.emplye_mangemnt_system.EMS.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRole {
    private Long id;
    private String title;
    private String description;

    public JobRole(Long id) {
        this.id = id;
    }
}
