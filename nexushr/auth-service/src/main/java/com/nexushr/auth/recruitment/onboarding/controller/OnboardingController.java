package com.nexushr.auth.recruitment.onboarding.controller;

import com.nexushr.auth.recruitment.candidate.model.Candidate;
import com.nexushr.auth.recruitment.onboarding.service.OnboardingService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final OnboardingService onboardingService;

    public OnboardingController(
            OnboardingService onboardingService) {

        this.onboardingService = onboardingService;
    }

    @PutMapping("/{id}")
    public Candidate onboard(
            @PathVariable Long id) {

        return onboardingService.onboard(id);
    }
}