package com.company.employee_payroll_system.controller;

import com.company.employee_payroll_system.dto.UserRequestDto;
import com.company.employee_payroll_system.dto.UserResponseDto;
import com.company.employee_payroll_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(
            @RequestBody UserRequestDto requestDto) {

        return userService.createUser(requestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }
}