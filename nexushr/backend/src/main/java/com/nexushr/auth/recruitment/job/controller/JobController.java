package com.nexushr.auth.recruitment.job.controller;

import com.nexushr.auth.recruitment.job.model.Job;
import com.nexushr.auth.recruitment.job.service.JobService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(
            JobService jobService) {

        this.jobService = jobService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Job createJob(
            @RequestBody Job job) {

        return jobService.createJob(job);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'HR', 'RECRUITER', 'ADMIN')")
    public List<Job> getAllJobs() {

        return jobService.getAllJobs();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public String deleteJob(
            @PathVariable Long id) {

        jobService.deleteJob(id);

        return "Job Deleted Successfully";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Job update(@PathVariable Long id, @RequestBody Job job) { return jobService.update(id, job); }

    @PutMapping("/{id}/publish")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Job publish(@PathVariable Long id) { return jobService.publish(id); }

    @PutMapping("/{id}/close")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Job close(@PathVariable Long id) { return jobService.close(id); }
}