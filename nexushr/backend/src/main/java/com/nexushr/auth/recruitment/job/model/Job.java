package com.nexushr.auth.recruitment.job.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;

    private String department;

    private String location;

    private Integer vacancies;

    private String description;

    private String status;
    private String requiredSkills;
    private String experience;
    private String salaryRange;
    private String employmentType;
    private boolean internalPosting;
    private boolean externalPosting;
    private String requisitionRequestedBy;

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getVacancies() {
        return vacancies;
    }

    public void setVacancies(Integer vacancies) {
        this.vacancies = vacancies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String value) { requiredSkills = value; }
    public String getExperience() { return experience; }
    public void setExperience(String value) { experience = value; }
    public String getSalaryRange() { return salaryRange; }
    public void setSalaryRange(String value) { salaryRange = value; }
    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String value) { employmentType = value; }
    public boolean isInternalPosting() { return internalPosting; }
    public void setInternalPosting(boolean value) { internalPosting = value; }
    public boolean isExternalPosting() { return externalPosting; }
    public void setExternalPosting(boolean value) { externalPosting = value; }
    public String getRequisitionRequestedBy() { return requisitionRequestedBy; }
    public void setRequisitionRequestedBy(String value) { requisitionRequestedBy = value; }
}