package com.company.employee_payroll_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    private String roleName;
}