package com.nexushr.auth.recruitment.onboarding.service;

import com.nexushr.auth.recruitment.candidate.model.Candidate;
import com.nexushr.auth.recruitment.candidate.model.CandidateStatus;
import com.nexushr.auth.recruitment.candidate.repository.CandidateRepository;

import org.springframework.stereotype.Service;

@Service
public class OnboardingService {

    private final CandidateRepository candidateRepository;

    public OnboardingService(
            CandidateRepository candidateRepository) {

        this.candidateRepository = candidateRepository;
    }

    public Candidate onboard(Long id) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Candidate Not Found"));

        if (candidate.getStatus()
                != CandidateStatus.SELECTED) {

            throw new RuntimeException(
                    "Only SELECTED candidates can be onboarded");
        }

        candidate.setStatus(
                CandidateStatus.ONBOARDED);

        return candidateRepository.save(candidate);
    }
}