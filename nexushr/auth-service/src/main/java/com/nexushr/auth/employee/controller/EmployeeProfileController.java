package com.nexushr.auth.employee.controller;

import com.nexushr.auth.employee.dto.ProfileUpdateRequest;
import com.nexushr.auth.employee.model.Employee;
import com.nexushr.auth.employee.service.EmployeeProfileService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(
            EmployeeProfileService employeeProfileService) {

        this.employeeProfileService = employeeProfileService;
    }

    @GetMapping
    public Optional<Employee> getProfile(
            Authentication authentication) {

        String email = authentication.getName();

        return employeeProfileService.getProfile(email);
    }

    @PutMapping
    public Employee updateProfile(
            Authentication authentication,
            @RequestBody ProfileUpdateRequest request) {

        String email = authentication.getName();

        return employeeProfileService.updateProfile(
                email,
                request.getPhoneNumber(),
                request.getAddress(),
                request.getEmergencyContactName(),
                request.getEmergencyContactPhone(),
                request.getEmergencyContactRelation()
        );
    }
}