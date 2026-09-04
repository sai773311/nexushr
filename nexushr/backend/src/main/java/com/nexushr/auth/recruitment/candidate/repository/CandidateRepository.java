package com.nexushr.auth.recruitment.candidate.repository;

import com.nexushr.auth.recruitment.candidate.model.Candidate;
import com.nexushr.auth.recruitment.candidate.model.CandidateStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository
        extends JpaRepository<Candidate, Long> {

    long countByStatus(
            CandidateStatus status);
}