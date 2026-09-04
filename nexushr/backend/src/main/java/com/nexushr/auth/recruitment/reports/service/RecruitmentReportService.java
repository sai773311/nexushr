package com.nexushr.auth.recruitment.reports.service;

import com.nexushr.auth.recruitment.job.repository.JobRepository;
import com.nexushr.auth.recruitment.candidate.repository.CandidateRepository;
import com.nexushr.auth.recruitment.candidate.model.CandidateStatus;
import com.nexushr.auth.recruitment.interview.repository.InterviewRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecruitmentReportService {

    private final JobRepository jobRepository;

    private final CandidateRepository candidateRepository;

    private final InterviewRepository interviewRepository;

    public RecruitmentReportService(
            JobRepository jobRepository,
            CandidateRepository candidateRepository,
            InterviewRepository interviewRepository) {

        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
        this.interviewRepository = interviewRepository;
    }

    public Map<String, Long> getSummary() {

        Map<String, Long> report =
                new HashMap<>();

        report.put(
                "jobsCount",
                jobRepository.count());

        report.put(
                "candidatesCount",
                candidateRepository.count());

        report.put(
                "interviewsCount",
                interviewRepository.count());

        report.put(
                "selectedCandidates",
                candidateRepository.countByStatus(
                        CandidateStatus.SELECTED));

        return report;
    }
}