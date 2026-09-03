package com.nexushr.auth.document.controller;

import com.nexushr.auth.document.model.EmployeeDocument;
import com.nexushr.auth.document.service.EmployeeDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/documents")
public class EmployeeDocumentController {

    private final EmployeeDocumentService documentService;

    public EmployeeDocumentController(
            EmployeeDocumentService documentService) {
        this.documentService = documentService;
    }

    // Create / Upload document record
    @PostMapping
    public ResponseEntity<EmployeeDocument> createDocument(
            @RequestBody EmployeeDocument document) {

        return ResponseEntity.ok(
                documentService.createDocument(document));
    }

    // Get all documents
    @GetMapping
    public ResponseEntity<List<EmployeeDocument>> getAllDocuments() {

        return ResponseEntity.ok(
                documentService.getAllDocuments());
    }

    // Get document by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDocument> getDocumentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                documentService.getDocumentById(id));
    }

    // Get documents by employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeDocument>> getDocumentsByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                documentService.getDocumentsByEmployee(employeeId));
    }

    // Update document
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDocument> updateDocument(
            @PathVariable Long id,
            @RequestBody EmployeeDocument document) {

        return ResponseEntity.ok(
                documentService.updateDocument(id, document));
    }

        @PutMapping("/{id}/verify")
        @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
        public ResponseEntity<EmployeeDocument> verify(@PathVariable Long id, @RequestParam String status, @RequestParam(defaultValue = "") String notes) {
                EmployeeDocument document = documentService.getDocumentById(id);
                document.setStatus(status.toUpperCase()); document.setVerificationNotes(notes);
                return ResponseEntity.ok(documentService.createDocument(document));
        }

    // Delete document
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(
            @PathVariable Long id) {

        documentService.deleteDocument(id);

        return ResponseEntity.ok(
                "Document deleted successfully");
    }
}