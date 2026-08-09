package com.company.employee_payroll_system.repository;

import com.company.employee_payroll_system.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeCode(String employeeCode);

    Optional<Employee> findByEmail(String email);

    Page<Employee> findByFirstNameContainingIgnoreCase(
            String firstName,
            Pageable pageable
    );
}