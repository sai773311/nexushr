package com.nexushr.auth.workflow.repository;

import com.nexushr.auth.workflow.model.ApprovalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApprovalRequestRepository extends JpaRepository<ApprovalRequest, Long> {
    List<ApprovalRequest> findByRequesterEmailOrderByCreatedAtDesc(String email);
    List<ApprovalRequest> findByStatusOrderByCreatedAtDesc(String status);
}