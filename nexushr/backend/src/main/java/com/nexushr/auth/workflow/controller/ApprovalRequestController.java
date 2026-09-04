package com.nexushr.auth.workflow.controller;

import com.nexushr.auth.workflow.model.ApprovalRequest;
import com.nexushr.auth.workflow.service.ApprovalRequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/workflows")
public class ApprovalRequestController {
    private final ApprovalRequestService service;
    public ApprovalRequestController(ApprovalRequestService service) { this.service = service; }
    @PostMapping @PreAuthorize("isAuthenticated()") public ApprovalRequest create(@RequestBody ApprovalRequest request, Authentication authentication) { return service.create(request, authentication.getName()); }
    @GetMapping("/mine") @PreAuthorize("isAuthenticated()") public List<ApprovalRequest> mine(Authentication authentication) { return service.mine(authentication.getName()); }
    @GetMapping("/pending") @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')") public List<ApprovalRequest> pending() { return service.pending(); }
    @PutMapping("/{id}/review") @PreAuthorize("hasAnyRole('MANAGER', 'HR', 'ADMIN')") public ApprovalRequest review(@PathVariable Long id, @RequestParam boolean approved, Authentication authentication) { return service.review(id, approved, authentication.getName()); }
}