package com.nexushr.auth.asset.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "assets")
public class Asset {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String assetTag;
    private String assetType;
    private String description;
    private String serialNumber;
    private String status = "AVAILABLE";
    private String assignedEmployeeEmail;
    private LocalDate assignedDate;
    private LocalDate returnedDate;
    private String conditionNotes;

    public Asset() { }
    public Long getId() { return id; }
    public void setId(Long value) { id = value; }
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String value) { assetTag = value; }
    public String getAssetType() { return assetType; }
    public void setAssetType(String value) { assetType = value; }
    public String getDescription() { return description; }
    public void setDescription(String value) { description = value; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String value) { serialNumber = value; }
    public String getStatus() { return status; }
    public void setStatus(String value) { status = value; }
    public String getAssignedEmployeeEmail() { return assignedEmployeeEmail; }
    public void setAssignedEmployeeEmail(String value) { assignedEmployeeEmail = value; }
    public LocalDate getAssignedDate() { return assignedDate; }
    public void setAssignedDate(LocalDate value) { assignedDate = value; }
    public LocalDate getReturnedDate() { return returnedDate; }
    public void setReturnedDate(LocalDate value) { returnedDate = value; }
    public String getConditionNotes() { return conditionNotes; }
    public void setConditionNotes(String value) { conditionNotes = value; }
}