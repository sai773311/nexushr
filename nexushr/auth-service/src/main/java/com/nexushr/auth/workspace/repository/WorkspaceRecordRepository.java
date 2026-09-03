package com.nexushr.auth.workspace.repository;

import com.nexushr.auth.workspace.model.WorkspaceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkspaceRecordRepository extends JpaRepository<WorkspaceRecord, Long> {
    List<WorkspaceRecord> findByModuleOrderByUpdatedAtDesc(String module);
    List<WorkspaceRecord> findByModuleAndFeatureOrderByUpdatedAtDesc(String module, String feature);
}