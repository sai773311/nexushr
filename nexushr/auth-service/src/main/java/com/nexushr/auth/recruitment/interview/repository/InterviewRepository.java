package com.nexushr.auth.recruitment.interview.repository;

import com.nexushr.auth.recruitment.interview.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository
        extends JpaRepository<Interview, Long> {
}