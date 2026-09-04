package com.nexushr.auth.document.service;

import com.nexushr.auth.document.model.EmployeeDocument;
import com.nexushr.auth.document.repository.EmployeeDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDocumentService {

    private final EmployeeDocumentRepository documentRepository;

    public EmployeeDocumentService(
            EmployeeDocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    // Upload/Create document record
    public EmployeeDocument createDocument(EmployeeDocument document) {
        return documentRepository.save(document);
    }

    // Get all documents
    public List<EmployeeDocument> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Get document by ID
    public EmployeeDocument getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Document not found"));
    }

    // Get documents of an employee
    public List<EmployeeDocument> getDocumentsByEmployee(Long employeeId) {
        return documentRepository.findByEmployeeId(employeeId);
    }

    // Update document
    public EmployeeDocument updateDocument(
            Long id,
            EmployeeDocument updatedDocument) {

        EmployeeDocument existingDocument =
                documentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Document not found"));

        existingDocument.setEmployeeId(
                updatedDocument.getEmployeeId());

        existingDocument.setDocumentName(
                updatedDocument.getDocumentName());

        existingDocument.setDocumentType(
                updatedDocument.getDocumentType());

        existingDocument.setDocumentUrl(
                updatedDocument.getDocumentUrl());

        existingDocument.setUploadDate(
                updatedDocument.getUploadDate());

        existingDocument.setStatus(
                updatedDocument.getStatus());

        return documentRepository.save(existingDocument);
    }

    // Delete document
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document not found");
        }

        documentRepository.deleteById(id);
    }
}