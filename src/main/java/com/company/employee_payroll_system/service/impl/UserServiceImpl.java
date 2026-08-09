package com.company.employee_payroll_system.service.impl;

import com.company.employee_payroll_system.dto.UserRequestDto;
import com.company.employee_payroll_system.dto.UserResponseDto;
import com.company.employee_payroll_system.entity.Role;
import com.company.employee_payroll_system.entity.User;
import com.company.employee_payroll_system.repository.RoleRepository;
import com.company.employee_payroll_system.repository.UserRepository;
import com.company.employee_payroll_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {

        Role role = roleRepository.findById(requestDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .email(requestDto.getEmail())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto mapToResponseDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roleName(user.getRole().getRoleName())
                .build();
    }
}