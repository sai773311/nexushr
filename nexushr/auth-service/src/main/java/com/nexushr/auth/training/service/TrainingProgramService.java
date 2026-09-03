package com.nexushr.auth.training.service;

import com.nexushr.auth.training.model.TrainingProgram;
import com.nexushr.auth.training.repository.TrainingProgramRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingProgramService {
    private final TrainingProgramRepository repository;
    public TrainingProgramService(TrainingProgramRepository repository) { this.repository = repository; }
    public TrainingProgram create(TrainingProgram item) { return repository.save(item); }
    public List<TrainingProgram> all() { return repository.findAll(); }
    public List<TrainingProgram> mine(String email) { return repository.findByAssignedEmployeeEmail(email); }
    public TrainingProgram updateProgress(Long id, String email, Integer progress) {
        TrainingProgram item = repository.findById(id).orElseThrow();
        if (!email.equals(item.getAssignedEmployeeEmail())) throw new RuntimeException("Training is not assigned to this employee");
        if (progress < 0 || progress > 100) throw new IllegalArgumentException("Progress must be between 0 and 100");
        item.setProgress(progress); item.setStatus(progress == 100 ? "COMPLETED" : "IN_PROGRESS"); return repository.save(item);
    }
    public TrainingProgram assign(Long id, String email) { TrainingProgram item = repository.findById(id).orElseThrow(); item.setAssignedEmployeeEmail(email); item.setStatus("ASSIGNED"); return repository.save(item); }
}