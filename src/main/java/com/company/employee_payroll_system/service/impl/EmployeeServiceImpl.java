package com.company.employee_payroll_system.service.impl;

import com.company.employee_payroll_system.dto.EmployeeRequestDto;
import com.company.employee_payroll_system.dto.EmployeeResponseDto;
import com.company.employee_payroll_system.entity.Department;
import com.company.employee_payroll_system.entity.Employee;
import com.company.employee_payroll_system.entity.User;
import com.company.employee_payroll_system.exception.ResourceNotFoundException;
import com.company.employee_payroll_system.repository.DepartmentRepository;
import com.company.employee_payroll_system.repository.EmployeeRepository;
import com.company.employee_payroll_system.repository.UserRepository;
import com.company.employee_payroll_system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.company.employee_payroll_system.exception.DuplicateResourceException;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto requestDto) {

        if (employeeRepository.findByEmployeeCode(requestDto.getEmployeeCode()).isPresent()) {
            throw new DuplicateResourceException("Employee code already exists");
        }

        if (employeeRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Employee email already exists");
        }

        Department department = departmentRepository.findById(requestDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Employee employee = Employee.builder()
                .employeeCode(requestDto.getEmployeeCode())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .phone(requestDto.getPhone())
                .gender(requestDto.getGender())
                .dateOfBirth(requestDto.getDateOfBirth())
                .joiningDate(requestDto.getJoiningDate())
                .salary(requestDto.getSalary())
                .status(requestDto.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .department(department)
                .user(user)
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponseDto(savedEmployee);
    }

    @Override
    public Page<EmployeeResponseDto> getAllEmployees(
            int page,
            int size,
            String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy));

        return employeeRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return mapToResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto getEmployeeByEmail(String email) {

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with email: " + email));

        return mapToResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto getEmployeeByEmployeeCode(String employeeCode) {

        Employee employee = employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with employee code: " + employeeCode));

        return mapToResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployee(
            Long id,
            EmployeeRequestDto requestDto
    ) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"
                        ));

        Optional<Employee> existingEmployee =
                employeeRepository.findByEmployeeCode(
                        requestDto.getEmployeeCode()
                );

        if (existingEmployee.isPresent()
                &&
                !existingEmployee.get().getId().equals(id)) {

            throw new DuplicateResourceException(
                    "Employee code already exists"
            );
        }

        Department department =
                departmentRepository.findById(
                                requestDto.getDepartmentId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Department not found"
                                ));

        User user = userRepository.findById(
                        requestDto.getUserId()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        employee.setEmployeeCode(
                requestDto.getEmployeeCode()
        );

        employee.setFirstName(
                requestDto.getFirstName()
        );

        employee.setLastName(
                requestDto.getLastName()
        );

        employee.setEmail(
                requestDto.getEmail()
        );

        employee.setPhone(
                requestDto.getPhone()
        );

        employee.setGender(
                requestDto.getGender()
        );

        employee.setDateOfBirth(
                requestDto.getDateOfBirth()
        );

        employee.setJoiningDate(
                requestDto.getJoiningDate()
        );

        employee.setSalary(
                requestDto.getSalary()
        );

        employee.setStatus(
                requestDto.getStatus()
        );

        employee.setDepartment(department);

        employee.setUser(user);

        employee.setUpdatedAt(
                LocalDateTime.now()
        );

        Employee updatedEmployee =
                employeeRepository.save(employee);

        return mapToResponseDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }

    private EmployeeResponseDto mapToResponseDto(Employee employee) {

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .employeeCode(employee.getEmployeeCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .gender(employee.getGender())
                .dateOfBirth(employee.getDateOfBirth())
                .joiningDate(employee.getJoiningDate())
                .salary(employee.getSalary())
                .status(employee.getStatus())
                .departmentName(employee.getDepartment().getDepartmentName())
                .username(employee.getUser().getUsername())
                .createdAt(employee.getCreatedAt())
                .updatedAt(employee.getUpdatedAt())
                .build();
    }

    @Override
    public Page<EmployeeResponseDto> searchEmployees(
            String keyword,
            Pageable pageable
    ) {

        Page<Employee> employees =
                employeeRepository
                        .findByFirstNameContainingIgnoreCase(
                                keyword,
                                (Pageable) pageable
                        );

        return employees.map(this::mapToResponseDto);
    }
}