package com.company.employee_payroll_system.controller;

import com.company.employee_payroll_system.dto.DepartmentRequestDto;
import com.company.employee_payroll_system.dto.DepartmentResponseDto;
import com.company.employee_payroll_system.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponseDto createDepartment(
            @RequestBody DepartmentRequestDto requestDto) {

        return departmentService.createDepartment(requestDto);
    }

    @GetMapping
    public List<DepartmentResponseDto> getAllDepartments() {

        return departmentService.getAllDepartments();
    }
}