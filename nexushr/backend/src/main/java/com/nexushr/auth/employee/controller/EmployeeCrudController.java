package com.nexushr.auth.employee.controller;

import com.nexushr.auth.employee.model.Employee;
import com.nexushr.auth.employee.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.nexushr.auth.employee.history.model.EmployeeHistory;
import com.nexushr.auth.employee.model.EmployeeStatus;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/employees")
public class EmployeeCrudController {

    private final EmployeeService employeeService;

    public EmployeeCrudController(
            EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Employee createEmployee(
            @RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        employee.setId(id);

        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee Deleted Successfully";
    }

    @PatchMapping("/{id}/department")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Employee department(@PathVariable Long id, @RequestParam String value) { return employeeService.assignDepartment(id, value); }

    @PatchMapping("/{id}/designation")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Employee designation(@PathVariable Long id, @RequestParam String value) { return employeeService.assignDesignation(id, value); }

    @PatchMapping("/{id}/manager")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Employee manager(@PathVariable Long id, @RequestParam Long managerId) { return employeeService.assignManager(id, managerId); }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Employee status(@PathVariable Long id, @RequestParam EmployeeStatus value, @RequestParam(defaultValue = "") String reason) { return employeeService.changeStatus(id, value, reason); }

    @GetMapping("/{id}/history")
    public List<EmployeeHistory> history(@PathVariable Long id) { return employeeService.history(id); }
}