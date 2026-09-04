package com.nexushr.auth.recruitment.interview.controller;

import com.nexushr.auth.recruitment.interview.model.Interview;
import com.nexushr.auth.recruitment.interview.service.InterviewService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(
            InterviewService interviewService) {

        this.interviewService = interviewService;
    }

    @PostMapping("/schedule")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Interview schedule(
            @RequestBody Interview interview) {

        return interviewService.schedule(interview);
    }

    @GetMapping("/all")
    public List<Interview> getAll() {

        return interviewService.getAll();
    }

    @PutMapping("/{id}/complete")
    public Interview complete(
            @PathVariable Long id) {

        return interviewService.complete(id);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Interview cancel(
            @PathVariable Long id) {

        return interviewService.cancel(id);
    }

    @PutMapping("/{id}/reschedule")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Interview reschedule(@PathVariable Long id, @RequestParam java.time.LocalDateTime time) { return interviewService.reschedule(id, time); }

    @PutMapping("/{id}/feedback")
    @PreAuthorize("hasAnyRole('INTERVIEWER', 'HR', 'ADMIN')")
    public Interview feedback(@PathVariable Long id, @RequestParam Integer rating, @RequestParam String feedback, @RequestParam String recommendation) { return interviewService.feedback(id, rating, feedback, recommendation); }
}