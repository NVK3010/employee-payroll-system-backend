package com.company.employee_payroll_system.controller;

import com.company.employee_payroll_system.dto.EmployeeRequestDto;
import com.company.employee_payroll_system.dto.EmployeeResponseDto;
import com.company.employee_payroll_system.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDto createEmployee(
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        return employeeService.createEmployee(requestDto);
    }

    @GetMapping
    public Page<EmployeeResponseDto> getAllEmployees(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy) {

        return employeeService.getAllEmployees(
                page,
                size,
                sortBy);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/search/email")
    public EmployeeResponseDto getEmployeeByEmail(
            @RequestParam String email) {

        return employeeService.getEmployeeByEmail(email);
    }

    @GetMapping("/search/code")
    public EmployeeResponseDto getEmployeeByEmployeeCode(
            @RequestParam String employeeCode) {

        return employeeService.getEmployeeByEmployeeCode(employeeCode);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDto requestDto) {

        return employeeService.updateEmployee(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeResponseDto>>
    searchEmployees(

            @RequestParam String keyword,

            Pageable pageable
    ) {

        return ResponseEntity.ok(
                employeeService.searchEmployees(
                        keyword,
                        pageable
                )
        );
    }
}