package com.emplye_mangemnt_system.EMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRoleDTO {
    private Long id;
    private String title;
    private String description;
}
