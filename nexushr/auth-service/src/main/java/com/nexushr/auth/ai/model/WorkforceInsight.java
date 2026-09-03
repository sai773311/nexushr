package com.nexushr.auth.ai.model;

import jakarta.persistence.*;

@Entity
@Table(name = "workforce_insights")
public class WorkforceInsight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String insightType;

    private String insight;

    private String riskLevel;

    private String recommendation;

    public WorkforceInsight() {
    }

    public WorkforceInsight(
            Long employeeId,
            String insightType,
            String insight,
            String riskLevel,
            String recommendation) {

        this.employeeId = employeeId;
        this.insightType = insightType;
        this.insight = insight;
        this.riskLevel = riskLevel;
        this.recommendation = recommendation;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getInsightType() {
        return insightType;
    }

    public void setInsightType(String insightType) {
        this.insightType = insightType;
    }

    public String getInsight() {
        return insight;
    }

    public void setInsight(String insight) {
        this.insight = insight;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}