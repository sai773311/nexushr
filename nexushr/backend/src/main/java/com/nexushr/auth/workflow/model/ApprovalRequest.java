package com.nexushr.auth.workflow.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "approval_requests")
public class ApprovalRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String requestType;
    private Long referenceId;
    private String requesterEmail;
    private String reviewerEmail;
    private String reason;
    private String status = "PENDING";
    private Instant createdAt = Instant.now();
    private Instant reviewedAt;
    public ApprovalRequest() { }
    public Long getId() { return id; }
    public String getRequestType() { return requestType; }
    public void setRequestType(String value) { requestType = value; }
    public Long getReferenceId() { return referenceId; }
    public void setReferenceId(Long value) { referenceId = value; }
    public String getRequesterEmail() { return requesterEmail; }
    public void setRequesterEmail(String value) { requesterEmail = value; }
    public String getReviewerEmail() { return reviewerEmail; }
    public void setReviewerEmail(String value) { reviewerEmail = value; }
    public String getReason() { return reason; }
    public void setReason(String value) { reason = value; }
    public String getStatus() { return status; }
    public void setStatus(String value) { status = value; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(Instant value) { reviewedAt = value; }
}