package com.nexushr.auth.department.service;

import com.nexushr.auth.department.model.Department;
import com.nexushr.auth.department.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.nexushr.auth.employee.repository.EmployeeRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository) {

        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public Department createDepartment(
            Department department) {

        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(
            Long id) {

        return departmentRepository.findById(id);
    }

    public Department updateDepartment(
            Department department) {

        return departmentRepository.save(department);
    }

    public void deleteDepartment(
            Long id) {

        departmentRepository.deleteById(id);
    }

    public Department assignHead(Long id, Long employeeId) {
        Department department = getRequired(id); department.setDepartmentHeadId(employeeId);
        return departmentRepository.save(department);
    }

    public java.util.Map<String, Object> statistics(Long id) {
        Department department = getRequired(id);
        java.util.Map<String, Object> result = new java.util.LinkedHashMap<>();
        result.put("department", department.getDepartmentName());
        result.put("employeeCount", employeeRepository.countByDepartment(department.getDepartmentName()));
        result.put("budget", department.getBudget());
        result.put("children", departmentRepository.findByParentDepartmentId(id));
        return result;
    }

    public java.util.List<com.nexushr.auth.employee.model.Employee> employees(Long id) {
        return employeeRepository.findByDepartment(getRequired(id).getDepartmentName());
    }

    private Department getRequired(Long id) { return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found")); }
}