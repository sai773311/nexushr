package com.nexushr.auth.recruitment.job.repository;

import com.nexushr.auth.recruitment.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository
        extends JpaRepository<Job, Long> {
}