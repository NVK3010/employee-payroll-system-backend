package com.company.employee_payroll_system.service.impl;

import com.company.employee_payroll_system.dto.DepartmentRequestDto;
import com.company.employee_payroll_system.dto.DepartmentResponseDto;
import com.company.employee_payroll_system.entity.Department;
import com.company.employee_payroll_system.repository.DepartmentRepository;
import com.company.employee_payroll_system.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

        Department department = Department.builder()
                .departmentName(requestDto.getDepartmentName())
                .location(requestDto.getLocation())
                .build();

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentResponseDto.builder()
                .id(savedDepartment.getId())
                .departmentName(savedDepartment.getDepartmentName())
                .location(savedDepartment.getLocation())
                .build();
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(department -> DepartmentResponseDto.builder()
                        .id(department.getId())
                        .departmentName(department.getDepartmentName())
                        .location(department.getLocation())
                        .build())
                .collect(Collectors.toList());
    }
}