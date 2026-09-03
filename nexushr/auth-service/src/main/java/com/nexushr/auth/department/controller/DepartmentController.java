package com.nexushr.auth.department.controller;

import com.nexushr.auth.department.model.Department;
import com.nexushr.auth.department.service.DepartmentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(
            DepartmentService departmentService) {

        this.departmentService = departmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Department createDepartment(
            @RequestBody Department department) {

        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(
            @PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        department.setId(id);

        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public String deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "Department Deleted Successfully";
    }

    @PatchMapping("/{id}/head")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Department head(@PathVariable Long id, @RequestParam Long employeeId) { return departmentService.assignHead(id, employeeId); }

    @GetMapping("/{id}/employees")
    public Object employees(@PathVariable Long id) { return departmentService.employees(id); }

    @GetMapping("/{id}/statistics")
    public Object statistics(@PathVariable Long id) { return departmentService.statistics(id); }
}