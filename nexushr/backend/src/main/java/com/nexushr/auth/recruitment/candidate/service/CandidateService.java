package com.nexushr.auth.recruitment.candidate.service;

import com.nexushr.auth.recruitment.candidate.model.*;
import com.nexushr.auth.recruitment.candidate.repository.CandidateRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(
            CandidateRepository candidateRepository) {

        this.candidateRepository = candidateRepository;
    }

    public Candidate apply(
            Candidate candidate) {

        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAll() {

        return candidateRepository.findAll();
    }

    public Candidate shortlist(Long id) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow();

        candidate.setStatus(
                CandidateStatus.SHORTLISTED);

        return candidateRepository.save(candidate);
    }

    public Candidate screen(Long id, Integer score) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow();
        candidate.setScreeningScore(score); candidate.setStatus(CandidateStatus.SHORTLISTED);
        return candidateRepository.save(candidate);
    }

    public Candidate reject(Long id, String reason) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow();
        candidate.setRejectionReason(reason); candidate.setStatus(CandidateStatus.REJECTED);
        return candidateRepository.save(candidate);
    }

    public Candidate select(Long id) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow();

        candidate.setStatus(
                CandidateStatus.SELECTED);

        return candidateRepository.save(candidate);
    }

    public Candidate reject(Long id) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow();

        candidate.setStatus(
                CandidateStatus.REJECTED);

        return candidateRepository.save(candidate);
    }
}