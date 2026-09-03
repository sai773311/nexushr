package com.nexushr.auth.recruitment.interview.service;

import com.nexushr.auth.recruitment.interview.model.*;
import com.nexushr.auth.recruitment.interview.repository.InterviewRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewService(
            InterviewRepository interviewRepository) {

        this.interviewRepository = interviewRepository;
    }

    public Interview schedule(
            Interview interview) {

        return interviewRepository.save(interview);
    }

    public List<Interview> getAll() {

        return interviewRepository.findAll();
    }

    public Interview complete(Long id) {

        Interview interview =
                interviewRepository.findById(id)
                        .orElseThrow();

        interview.setStatus(
                InterviewStatus.COMPLETED);

        return interviewRepository.save(interview);
    }

    public Interview cancel(Long id) {

        Interview interview =
                interviewRepository.findById(id)
                        .orElseThrow();

        interview.setStatus(
                InterviewStatus.CANCELLED);

        return interviewRepository.save(interview);
    }

    public Interview reschedule(Long id, java.time.LocalDateTime time) {
        Interview interview = interviewRepository.findById(id).orElseThrow();
        if (interview.getStatus() != InterviewStatus.SCHEDULED) throw new RuntimeException("Only scheduled interviews can be rescheduled");
        interview.setInterviewTime(time); return interviewRepository.save(interview);
    }

    public Interview feedback(Long id, Integer rating, String feedback, String recommendation) {
        Interview interview = interviewRepository.findById(id).orElseThrow();
        interview.setRating(rating); interview.setFeedback(feedback); interview.setRecommendation(recommendation);
        interview.setStatus(InterviewStatus.COMPLETED); return interviewRepository.save(interview);
    }
}