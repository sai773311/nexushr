package com.nexushr.auth.recruitment.job.service;

import com.nexushr.auth.recruitment.job.model.Job;
import com.nexushr.auth.recruitment.job.repository.JobRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(Job job) {

        job.setStatus("OPEN");

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job update(Long id, Job update) {
        Job job = jobRepository.findById(id).orElseThrow();
        job.setJobTitle(update.getJobTitle()); job.setDepartment(update.getDepartment());
        job.setLocation(update.getLocation()); job.setVacancies(update.getVacancies());
        job.setDescription(update.getDescription()); job.setRequiredSkills(update.getRequiredSkills());
        job.setExperience(update.getExperience()); job.setSalaryRange(update.getSalaryRange());
        job.setEmploymentType(update.getEmploymentType());
        return jobRepository.save(job);
    }

    public Job publish(Long id) { return setStatus(id, "PUBLISHED"); }
    public Job close(Long id) { return setStatus(id, "CLOSED"); }
    private Job setStatus(Long id, String status) { Job job = jobRepository.findById(id).orElseThrow(); job.setStatus(status); return jobRepository.save(job); }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}