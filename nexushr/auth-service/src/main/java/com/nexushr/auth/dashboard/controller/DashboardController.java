package com.nexushr.auth.dashboard.controller;

import com.nexushr.auth.dashboard.model.DashboardSummary;
import com.nexushr.auth.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardSummary> getDashboard() {

        return ResponseEntity.ok(
                dashboardService.getDashboardSummary()
        );
    }

    @GetMapping("/me")
    public ResponseEntity<java.util.Map<String, Object>> getRoleDashboard(Authentication authentication) {
        String role = authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
        return ResponseEntity.ok(dashboardService.getRoleDashboard(authentication.getName(), role.replace("ROLE_", "")));
    }
}