package com.company.employee_payroll_system.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {

    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String gender;

    private LocalDate dateOfBirth;

    private LocalDate joiningDate;

    private BigDecimal salary;

    private String status;

    private String departmentName;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}