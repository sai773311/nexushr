package com.nexushr.auth.employee.history.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "employee_history")
public class EmployeeHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String action;
    private String details;
    private String performedBy;
    private Instant createdAt;

    public EmployeeHistory() { }
    public EmployeeHistory(Long employeeId, String action, String details, String performedBy) {
        this.employeeId = employeeId; this.action = action; this.details = details;
        this.performedBy = performedBy; this.createdAt = Instant.now();
    }
    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public String getAction() { return action; }
    public String getDetails() { return details; }
    public String getPerformedBy() { return performedBy; }
    public Instant getCreatedAt() { return createdAt; }
}