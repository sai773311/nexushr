package com.nexushr.auth.admin.controller;

import com.nexushr.auth.admin.service.AdminService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/employees")
    public Object getEmployees() {
        return adminService.getAllEmployees();
    }

    @GetMapping("/departments")
    public Object getDepartments() {
        return adminService.getAllDepartments();
    }

    @GetMapping("/designations")
    public Object getDesignations() {
        return adminService.getAllDesignations();
    }
}