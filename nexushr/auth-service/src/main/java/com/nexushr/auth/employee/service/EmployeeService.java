package com.nexushr.auth.employee.service;

import com.nexushr.auth.employee.model.Employee;
import com.nexushr.auth.employee.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import com.nexushr.auth.employee.history.model.EmployeeHistory;
import com.nexushr.auth.employee.history.repository.EmployeeHistoryRepository;
import com.nexushr.auth.employee.model.EmployeeStatus;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeHistoryRepository historyRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeHistoryRepository historyRepository) {

        this.employeeRepository = employeeRepository;
        this.historyRepository = historyRepository;
    }

    public Employee createEmployee(
            Employee employee) {
        if (employee.getStatus() == null) employee.setStatus(EmployeeStatus.ACTIVE);
        if (employee.getJoiningDate() == null) employee.setJoiningDate(LocalDate.now());
        Employee saved = employeeRepository.save(employee);
        record(saved, "JOINED", "Employee created", "system");
        return saved;
    }

    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(
            Long id) {

        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(
            Employee employee) {
        Employee saved = employeeRepository.save(employee);
        record(saved, "UPDATED", "Employee information updated", "hr");
        return saved;
    }

    public void deleteEmployee(
            Long id) {

        employeeRepository.deleteById(id);
    }

    public Employee assignDepartment(Long id, String department) {
        Employee employee = getRequired(id); employee.setDepartment(department);
        Employee saved = employeeRepository.save(employee); record(saved, "TRANSFERRED", "Department: " + department, "hr"); return saved;
    }

    public Employee assignDesignation(Long id, String designation) {
        Employee employee = getRequired(id); employee.setDesignation(designation);
        Employee saved = employeeRepository.save(employee); record(saved, "PROMOTED", "Designation: " + designation, "hr"); return saved;
    }

    public Employee assignManager(Long id, Long managerId) {
        Employee employee = getRequired(id); employee.setManagerId(managerId);
        Employee saved = employeeRepository.save(employee); record(saved, "MANAGER_ASSIGNED", "Manager ID: " + managerId, "hr"); return saved;
    }

    public Employee changeStatus(Long id, EmployeeStatus status, String reason) {
        Employee employee = getRequired(id); employee.setStatus(status); employee.setLastAction(status.name());
        employee.setActionDate(LocalDate.now()); employee.setActionReason(reason);
        Employee saved = employeeRepository.save(employee); record(saved, status.name(), reason, "hr"); return saved;
    }

    public List<EmployeeHistory> history(Long id) { return historyRepository.findByEmployeeIdOrderByCreatedAtDesc(id); }

    private Employee getRequired(Long id) { return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found")); }
    private void record(Employee employee, String action, String details, String performedBy) {
        historyRepository.save(new EmployeeHistory(employee.getId(), action, details, performedBy));
    }
}