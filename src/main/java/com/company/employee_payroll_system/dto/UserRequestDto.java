package com.company.employee_payroll_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    private String username;

    private String password;

    private String email;

    private Long roleId;
}