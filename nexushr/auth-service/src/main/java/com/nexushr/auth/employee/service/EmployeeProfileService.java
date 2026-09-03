package com.nexushr.auth.employee.service;

import com.nexushr.auth.employee.model.Employee;
import com.nexushr.auth.employee.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeProfileService {

    private final EmployeeRepository employeeRepository;

    public EmployeeProfileService(
            EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getProfile(
            String email) {

        return employeeRepository.findByEmail(email);
    }

    public Employee updateProfile(
            String email,
            String phoneNumber,
            String address,
            String emergencyContactName,
            String emergencyContactPhone,
            String emergencyContactRelation) {

        Employee employee =
                employeeRepository.findByEmail(email)
                        .orElseThrow();

        employee.setPhoneNumber(phoneNumber);
        employee.setAddress(address);
        employee.setEmergencyContactName(emergencyContactName);
        employee.setEmergencyContactPhone(emergencyContactPhone);
        employee.setEmergencyContactRelation(emergencyContactRelation);

        return employeeRepository.save(employee);
    }
}