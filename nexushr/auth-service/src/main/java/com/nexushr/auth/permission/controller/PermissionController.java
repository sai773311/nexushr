package com.nexushr.auth.permission.controller;

import com.nexushr.auth.permission.model.RolePermission;
import com.nexushr.auth.permission.repository.RolePermissionRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@PreAuthorize("hasRole('ADMIN')")
public class PermissionController {
    private final RolePermissionRepository repository;
    public PermissionController(RolePermissionRepository repository) { this.repository = repository; }
    @GetMapping("/{role}") public List<RolePermission> byRole(@PathVariable String role) { return repository.findByRoleName(role.toUpperCase()); }
    @PostMapping public RolePermission assign(@RequestBody RolePermission permission) { permission.setRoleName(permission.getRoleName().toUpperCase()); return repository.save(permission); }
    @PutMapping("/{id}") public RolePermission update(@PathVariable Long id, @RequestParam boolean enabled) { RolePermission item = repository.findById(id).orElseThrow(); item.setEnabled(enabled); return repository.save(item); }
}