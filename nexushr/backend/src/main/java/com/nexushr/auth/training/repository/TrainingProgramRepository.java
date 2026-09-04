package com.nexushr.auth.training.repository;

import com.nexushr.auth.training.model.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Long> {
    List<TrainingProgram> findByAssignedEmployeeEmail(String email);
}