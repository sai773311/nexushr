package com.nexushr.auth.employee.history.repository;

import com.nexushr.auth.employee.history.model.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistory, Long> {
    List<EmployeeHistory> findByEmployeeIdOrderByCreatedAtDesc(Long employeeId);
}