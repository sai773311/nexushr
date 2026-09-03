package com.nexushr.auth.leave.repository;

import com.nexushr.auth.leave.model.Leave;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository
        extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeEmail(
            String employeeEmail);
        long countByEmployeeEmailAndStatus(String email, com.nexushr.auth.leave.model.LeaveStatus status);
        long countByStatus(com.nexushr.auth.leave.model.LeaveStatus status);
}