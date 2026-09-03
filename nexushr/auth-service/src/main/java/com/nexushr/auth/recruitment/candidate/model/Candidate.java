package com.nexushr.auth.recruitment.candidate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String appliedJob;
    private String resumeUrl;
    private String skills;
    private Integer screeningScore;
    private String rejectionReason;
    private String offerStatus;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status =
            CandidateStatus.APPLIED;

    public Candidate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAppliedJob() {
        return appliedJob;
    }

    public void setAppliedJob(String appliedJob) {
        this.appliedJob = appliedJob;
    }

    public CandidateStatus getStatus() {
        return status;
    }

    public void setStatus(CandidateStatus status) {
        this.status = status;
    }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String value) { resumeUrl = value; }
    public String getSkills() { return skills; }
    public void setSkills(String value) { skills = value; }
    public Integer getScreeningScore() { return screeningScore; }
    public void setScreeningScore(Integer value) { screeningScore = value; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String value) { rejectionReason = value; }
    public String getOfferStatus() { return offerStatus; }
    public void setOfferStatus(String value) { offerStatus = value; }
}