package com.nexushr.auth.document.repository;

import com.nexushr.auth.document.model.EmployeeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDocumentRepository
        extends JpaRepository<EmployeeDocument, Long> {

    List<EmployeeDocument> findByEmployeeId(Long employeeId);
}