package com.nexushr.auth.performance.controller;

import com.nexushr.auth.performance.model.Performance;
import com.nexushr.auth.performance.service.PerformanceService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(
            PerformanceService performanceService) {

        this.performanceService =
                performanceService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')")
    public Performance create(
            @RequestBody Performance performance) {

        return performanceService.create(
                performance);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')")
    public List<Performance> getAll() {

        return performanceService.getAll();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Performance> getMy(Authentication authentication) { return performanceService.getMy(authentication.getName()); }

    @PutMapping("/{id}/review")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')")
    public Performance review(
            @PathVariable Long id,
            @RequestParam Integer rating,
            @RequestParam String feedback) {

        return performanceService.review(
                id,
                rating,
                feedback);
    }

    @PutMapping("/{id}/progress")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Performance progress(@PathVariable Long id, @RequestParam Integer value, @RequestParam String selfAssessment) { return performanceService.progress(id, value, selfAssessment); }

    @PutMapping("/{id}/improvement-plan")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')")
    public Performance improvementPlan(@PathVariable Long id, @RequestParam boolean enabled) { return performanceService.improvementPlan(id, enabled); }
}