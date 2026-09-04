package com.nexushr.auth.performance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeEmail;

    private String goal;

    private String kpi;

    private Integer rating;

    private String managerFeedback;
    private Integer progress;
    private String selfAssessment;
    private String peerFeedback;
    private boolean improvementPlan;

    @Enumerated(EnumType.STRING)
    private PerformanceStatus status =
            PerformanceStatus.PENDING;

    public Performance() {
    }

    public Long getId() {
        return id;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(
            String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getKpi() {
        return kpi;
    }

    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getManagerFeedback() {
        return managerFeedback;
    }

    public void setManagerFeedback(
            String managerFeedback) {
        this.managerFeedback =
                managerFeedback;
    }

    public PerformanceStatus getStatus() {
        return status;
    }

    public void setStatus(
            PerformanceStatus status) {
        this.status = status;
    }

    public Integer getProgress() { return progress; }
    public void setProgress(Integer value) { progress = value; }
    public String getSelfAssessment() { return selfAssessment; }
    public void setSelfAssessment(String value) { selfAssessment = value; }
    public String getPeerFeedback() { return peerFeedback; }
    public void setPeerFeedback(String value) { peerFeedback = value; }
    public boolean isImprovementPlan() { return improvementPlan; }
    public void setImprovementPlan(boolean value) { improvementPlan = value; }
}