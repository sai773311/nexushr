package com.nexushr.auth.audit.controller;

import com.nexushr.auth.audit.model.AuditLog;
import com.nexushr.auth.audit.service.AuditLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@PreAuthorize("hasRole('ADMIN')")
public class AuditLogController {
    private final AuditLogService service;
    public AuditLogController(AuditLogService service) { this.service = service; }
    @GetMapping public List<AuditLog> all() { return service.all(); }
}