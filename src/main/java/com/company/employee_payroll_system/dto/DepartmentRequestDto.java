package com.company.employee_payroll_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDto {

    private String departmentName;

    private String location;
}