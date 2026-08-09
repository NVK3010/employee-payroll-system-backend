package com.company.employee_payroll_system.service;

import com.company.employee_payroll_system.dto.DepartmentRequestDto;
import com.company.employee_payroll_system.dto.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);

    List<DepartmentResponseDto> getAllDepartments();
}