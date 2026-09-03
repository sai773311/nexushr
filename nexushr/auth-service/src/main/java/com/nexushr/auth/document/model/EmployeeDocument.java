package com.nexushr.auth.document.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_documents")
public class EmployeeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private String documentName;

    private String documentType;

    private String documentUrl;

    private String uploadDate;

    private String status;
    private java.time.LocalDate expiryDate;
    private String verificationNotes;

    public EmployeeDocument() {
    }

    public EmployeeDocument(Long employeeId,
                             String documentName,
                             String documentType,
                             String documentUrl,
                             String uploadDate,
                             String status) {
        this.employeeId = employeeId;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentUrl = documentUrl;
        this.uploadDate = uploadDate;
        this.status = status;
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

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.time.LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(java.time.LocalDate value) { expiryDate = value; }
    public String getVerificationNotes() { return verificationNotes; }
    public void setVerificationNotes(String value) { verificationNotes = value; }
}