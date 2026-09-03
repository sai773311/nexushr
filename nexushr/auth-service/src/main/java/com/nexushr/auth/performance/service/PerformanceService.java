package com.nexushr.auth.performance.service;

import com.nexushr.auth.performance.model.*;
import com.nexushr.auth.performance.repository.PerformanceRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceRepository repository;

    public PerformanceService(
            PerformanceRepository repository) {

        this.repository = repository;
    }

    public Performance create(
            Performance performance) {

        return repository.save(performance);
    }

    public List<Performance> getAll() {

        return repository.findAll();
    }

    public List<Performance> getMy(String email) {
        return repository.findByEmployeeEmail(email);
    }

    public Performance review(
            Long id,
            Integer rating,
            String feedback) {

        Performance performance =
                repository.findById(id)
                        .orElseThrow();

        performance.setRating(rating);

        performance.setManagerFeedback(
                feedback);

        performance.setStatus(
                PerformanceStatus.REVIEWED);

        return repository.save(performance);
    }

    public Performance progress(Long id, Integer value, String selfAssessment) {
        Performance performance = repository.findById(id).orElseThrow();
        if (value < 0 || value > 100) throw new IllegalArgumentException("Progress must be between 0 and 100");
        performance.setProgress(value); performance.setSelfAssessment(selfAssessment);
        performance.setStatus(PerformanceStatus.IN_PROGRESS); return repository.save(performance);
    }

    public Performance improvementPlan(Long id, boolean enabled) {
        Performance performance = repository.findById(id).orElseThrow();
        performance.setImprovementPlan(enabled); return repository.save(performance);
    }
}