package com.nexushr.auth.audit.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String actor;
    private String action;
    private String entityType;
    private String entityId;
    private String oldValue;
    private String newValue;
    private String ipAddress;
    private Instant createdAt = Instant.now();

    public AuditLog() { }
    public AuditLog(String actor, String action, String entityType, String entityId, String oldValue, String newValue, String ipAddress) {
        this.actor = actor; this.action = action; this.entityType = entityType; this.entityId = entityId;
        this.oldValue = oldValue; this.newValue = newValue; this.ipAddress = ipAddress;
    }
    public Long getId() { return id; }
    public String getActor() { return actor; }
    public String getAction() { return action; }
    public String getEntityType() { return entityType; }
    public String getEntityId() { return entityId; }
    public String getOldValue() { return oldValue; }
    public String getNewValue() { return newValue; }
    public String getIpAddress() { return ipAddress; }
    public Instant getCreatedAt() { return createdAt; }
}