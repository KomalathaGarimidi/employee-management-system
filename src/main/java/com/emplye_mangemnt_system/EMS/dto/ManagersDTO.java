package com.emplye_mangemnt_system.EMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagersDTO {
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
}
