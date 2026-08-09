package com.company.employee_payroll_system.service;

import com.company.employee_payroll_system.dto.EmployeeRequestDto;
import com.company.employee_payroll_system.dto.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto);

    Page<EmployeeResponseDto> getAllEmployees(
            int page,
            int size,
            String sortBy);

    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto requestDto);

    void deleteEmployee(Long id);

    EmployeeResponseDto getEmployeeByEmail(String email);

    EmployeeResponseDto getEmployeeByEmployeeCode(String employeeCode);

    Page<EmployeeResponseDto> searchEmployees(
            String keyword,
            Pageable pageable
    );
}