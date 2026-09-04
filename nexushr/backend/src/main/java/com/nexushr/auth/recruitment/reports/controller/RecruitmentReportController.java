package com.nexushr.auth.recruitment.reports.controller;

import com.nexushr.auth.recruitment.reports.service.RecruitmentReportService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recruitment/reports")
public class RecruitmentReportController {

    private final RecruitmentReportService
            recruitmentReportService;

    public RecruitmentReportController(
            RecruitmentReportService recruitmentReportService) {

        this.recruitmentReportService =
                recruitmentReportService;
    }

    @GetMapping("/summary")
    public Map<String, Long> getSummary() {

        return recruitmentReportService
                .getSummary();
    }
}