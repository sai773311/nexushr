package com.nexushr.auth.workspace.service;

import com.nexushr.auth.workspace.model.WorkspaceRecord;
import com.nexushr.auth.workspace.repository.WorkspaceRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkspaceRecordService {
    private final WorkspaceRecordRepository repository;

    public WorkspaceRecordService(WorkspaceRecordRepository repository) {
        this.repository = repository;
    }

    public List<WorkspaceRecord> list(String module, String feature) {
        return feature == null || feature.isBlank()
                ? repository.findByModuleOrderByUpdatedAtDesc(module)
                : repository.findByModuleAndFeatureOrderByUpdatedAtDesc(module, feature);
    }

    public WorkspaceRecord create(String module, WorkspaceRecord record, String ownerEmail) {
        record.setId(null);
        record.setModule(module);
        record.setOwnerEmail(ownerEmail);
        return repository.save(record);
    }

    public WorkspaceRecord update(Long id, WorkspaceRecord changes) {
        WorkspaceRecord record = repository.findById(id).orElseThrow();
        record.setFeature(changes.getFeature());
        record.setName(changes.getName());
        record.setStatus(changes.getStatus());
        record.setDetails(changes.getDetails());
        return repository.save(record);
    }

    public void delete(Long id) { repository.deleteById(id); }
}