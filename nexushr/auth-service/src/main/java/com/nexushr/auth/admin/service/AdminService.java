package com.nexushr.auth.admin.service;

import com.nexushr.auth.employee.model.Employee;
import com.nexushr.auth.employee.repository.EmployeeRepository;

import com.nexushr.auth.department.model.Department;
import com.nexushr.auth.department.repository.DepartmentRepository;

import com.nexushr.auth.designation.model.Designation;
import com.nexushr.auth.designation.repository.DesignationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DesignationRepository designationRepository;

    public AdminService(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            DesignationRepository designationRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.designationRepository = designationRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }
}