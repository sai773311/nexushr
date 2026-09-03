package com.nexushr.auth.recruitment.candidate.controller;

import com.nexushr.auth.recruitment.candidate.model.Candidate;
import com.nexushr.auth.recruitment.candidate.service.CandidateService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(
            CandidateService candidateService) {

        this.candidateService = candidateService;
    }

    @PostMapping("/apply")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'HR', 'RECRUITER', 'ADMIN')")
    public Candidate apply(
            @RequestBody Candidate candidate) {

        return candidateService.apply(candidate);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public List<Candidate> getAll() {

        return candidateService.getAll();
    }

    @PutMapping("/{id}/shortlist")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Candidate shortlist(
            @PathVariable Long id) {

        return candidateService.shortlist(id);
    }

    @PutMapping("/{id}/select")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Candidate select(
            @PathVariable Long id) {

        return candidateService.select(id);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Candidate reject(@PathVariable Long id, @RequestParam(defaultValue = "") String reason) {

        return candidateService.reject(id, reason);
    }

    @PutMapping("/{id}/screen")
    @PreAuthorize("hasAnyRole('HR', 'RECRUITER', 'ADMIN')")
    public Candidate screen(@PathVariable Long id, @RequestParam Integer score) { return candidateService.screen(id, score); }
}