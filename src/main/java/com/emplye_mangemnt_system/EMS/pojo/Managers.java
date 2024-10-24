package com.emplye_mangemnt_system.EMS.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Managers {
    private Long managerId;
    private Long employeeId;
    private String managementLevel;
    private String department;
    private Integer maxDirectReports;
    private BigDecimal departmentBudget;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;
    private Long reportingManagerId;

   public Managers(long id){
       this.managerId=id;
   }
}
