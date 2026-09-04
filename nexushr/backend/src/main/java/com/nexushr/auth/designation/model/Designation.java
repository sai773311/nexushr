package com.nexushr.auth.designation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "designations")
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designationName;

    private String description;
    private String jobLevel;
    private String salaryGrade;
    private String responsibilities;
    private String requiredSkills;
    private Long departmentId;
    private Long nextDesignationId;

    @Enumerated(EnumType.STRING)
    private DesignationStatus status;

    public Designation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DesignationStatus getStatus() {
        return status;
    }

    public void setStatus(DesignationStatus status) {
        this.status = status;
    }

    public String getJobLevel() { return jobLevel; }
    public void setJobLevel(String value) { jobLevel = value; }
    public String getSalaryGrade() { return salaryGrade; }
    public void setSalaryGrade(String value) { salaryGrade = value; }
    public String getResponsibilities() { return responsibilities; }
    public void setResponsibilities(String value) { responsibilities = value; }
    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String value) { requiredSkills = value; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long value) { departmentId = value; }
    public Long getNextDesignationId() { return nextDesignationId; }
    public void setNextDesignationId(Long value) { nextDesignationId = value; }
}