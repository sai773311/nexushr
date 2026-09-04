package com.nexushr.auth.performance.repository;

import com.nexushr.auth.performance.model.Performance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository
        extends JpaRepository<Performance, Long> {
        java.util.List<Performance> findByEmployeeEmail(String employeeEmail);
}