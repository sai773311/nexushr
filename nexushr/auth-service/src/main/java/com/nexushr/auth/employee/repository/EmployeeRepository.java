package com.nexushr.auth.employee.repository;

import com.nexushr.auth.employee.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeId(
            String employeeId);

    Optional<Employee> findByEmail(
            String email);

        long countByDepartment(String department);
        java.util.List<Employee> findByDepartment(String department);
}