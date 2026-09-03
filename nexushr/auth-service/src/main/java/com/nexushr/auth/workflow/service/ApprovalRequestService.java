package com.nexushr.auth.workflow.service;

import com.nexushr.auth.workflow.model.ApprovalRequest;
import com.nexushr.auth.workflow.repository.ApprovalRequestRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class ApprovalRequestService {
    private final ApprovalRequestRepository repository;
    public ApprovalRequestService(ApprovalRequestRepository repository) { this.repository = repository; }
    public ApprovalRequest create(ApprovalRequest request, String email) { request.setRequesterEmail(email); request.setStatus("PENDING"); return repository.save(request); }
    public List<ApprovalRequest> mine(String email) { return repository.findByRequesterEmailOrderByCreatedAtDesc(email); }
    public List<ApprovalRequest> pending() { return repository.findByStatusOrderByCreatedAtDesc("PENDING"); }
    public ApprovalRequest review(Long id, boolean approved, String reviewer) { ApprovalRequest request = repository.findById(id).orElseThrow(); request.setStatus(approved ? "APPROVED" : "REJECTED"); request.setReviewerEmail(reviewer); request.setReviewedAt(Instant.now()); return repository.save(request); }
}