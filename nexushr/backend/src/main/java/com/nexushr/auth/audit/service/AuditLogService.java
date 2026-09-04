package com.nexushr.auth.audit.service;

import com.nexushr.auth.audit.model.AuditLog;
import com.nexushr.auth.audit.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {
    private final AuditLogRepository repository;
    public AuditLogService(AuditLogRepository repository) { this.repository = repository; }
    public AuditLog record(String actor, String action, String entityType, String entityId, String oldValue, String newValue, String ipAddress) {
        return repository.save(new AuditLog(actor, action, entityType, entityId, oldValue, newValue, ipAddress));
    }
    public java.util.List<AuditLog> all() { return repository.findAll(); }
}