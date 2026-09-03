package com.nexushr.auth.payroll.repository;

import com.nexushr.auth.payroll.model.Payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository
        extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployeeEmail(
            String employeeEmail);

        long countByStatus(com.nexushr.auth.payroll.model.PayrollStatus status);
}