package com.nexushr.auth.ai.repository;

import com.nexushr.auth.ai.model.WorkforceInsight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkforceInsightRepository
        extends JpaRepository<WorkforceInsight, Long> {

    List<WorkforceInsight> findByEmployeeId(Long employeeId);

    List<WorkforceInsight> findByInsightType(String insightType);

    List<WorkforceInsight> findByRiskLevel(String riskLevel);
}