package com.nexushr.auth.training.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "training_programs")
public class TrainingProgram {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String title;
    private String description;
    private String requiredSkills;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status = "PLANNED";
    private String assignedEmployeeEmail;
    private Integer progress = 0;
    private String certificateUrl;

    public TrainingProgram() { }
    public Long getId() { return id; }
    public void setId(Long value) { id = value; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getDescription() { return description; }
    public void setDescription(String value) { description = value; }
    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String value) { requiredSkills = value; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate value) { startDate = value; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate value) { endDate = value; }
    public String getStatus() { return status; }
    public void setStatus(String value) { status = value; }
    public String getAssignedEmployeeEmail() { return assignedEmployeeEmail; }
    public void setAssignedEmployeeEmail(String value) { assignedEmployeeEmail = value; }
    public Integer getProgress() { return progress; }
    public void setProgress(Integer value) { progress = value; }
    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String value) { certificateUrl = value; }
}