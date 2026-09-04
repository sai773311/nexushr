package com.nexushr.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nexushr.auth.dashboard.service.DashboardService;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final DashboardService dashboardService;

    public EmployeeController(DashboardService dashboardService) { this.dashboardService = dashboardService; }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Object dashboard(Authentication authentication) {
        return dashboardService.getRoleDashboard(authentication.getName(), "EMPLOYEE");
    }
}