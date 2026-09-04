package com.nexushr.auth.workspace.controller;

import com.nexushr.auth.workspace.model.WorkspaceRecord;
import com.nexushr.auth.workspace.service.WorkspaceRecordService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceRecordController {
    private final WorkspaceRecordService service;

    public WorkspaceRecordController(WorkspaceRecordService service) {
        this.service = service;
    }

    @GetMapping("/{module}")
    public List<WorkspaceRecord> list(@PathVariable String module,
                                      @RequestParam(required = false) String feature) {
        return service.list(module, feature);
    }

    @PostMapping("/{module}")
    public WorkspaceRecord create(@PathVariable String module,
                                   @RequestBody WorkspaceRecord record,
                                   Authentication authentication) {
        return service.create(module, record, authentication.getName());
    }

    @PutMapping("/{module}/{id}")
    public WorkspaceRecord update(@PathVariable Long id,
                                  @RequestBody WorkspaceRecord record) {
        return service.update(id, record);
    }

    @DeleteMapping("/{module}/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}