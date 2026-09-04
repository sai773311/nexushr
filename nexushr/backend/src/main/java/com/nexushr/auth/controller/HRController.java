package com.nexushr.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.nexushr.auth.employee.repository.EmployeeRepository;
import com.nexushr.auth.department.repository.DepartmentRepository;
import com.nexushr.auth.leave.repository.LeaveRepository;
import com.nexushr.auth.leave.model.LeaveStatus;
import com.nexushr.auth.recruitment.job.repository.JobRepository;
import com.nexushr.auth.recruitment.candidate.repository.CandidateRepository;
import com.nexushr.auth.recruitment.interview.repository.InterviewRepository;
import java.util.Map;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/hr")
public class HRController {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final LeaveRepository leaveRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final InterviewRepository interviewRepository;

    public HRController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
            LeaveRepository leaveRepository, JobRepository jobRepository, CandidateRepository candidateRepository,
            InterviewRepository interviewRepository) {
        this.employeeRepository = employeeRepository; this.departmentRepository = departmentRepository;
        this.leaveRepository = leaveRepository; this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository; this.interviewRepository = interviewRepository;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('HR')")
    public Map<String, Long> dashboard() {
        Map<String, Long> result = new LinkedHashMap<>();
        result.put("employees", employeeRepository.count()); result.put("departments", departmentRepository.count());
        result.put("pendingLeaves", leaveRepository.countByStatus(LeaveStatus.PENDING));
        result.put("openJobs", jobRepository.count()); result.put("candidates", candidateRepository.count());
        result.put("interviews", interviewRepository.count()); return result;
    }
}