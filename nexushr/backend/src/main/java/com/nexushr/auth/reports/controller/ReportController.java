package com.nexushr.auth.reports.controller;

import com.nexushr.auth.reports.service.ReportService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(
            ReportService reportService) {

        this.reportService = reportService;
    }

    @GetMapping("/dashboard")
    public Map<String, Long> dashboard() {

        return reportService
                .getDashboardSummary();
    }
}