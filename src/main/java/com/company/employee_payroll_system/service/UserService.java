package com.company.employee_payroll_system.service;

import com.company.employee_payroll_system.dto.UserRequestDto;
import com.company.employee_payroll_system.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto requestDto);

    List<UserResponseDto> getAllUsers();
}