package com.nexushr.auth.permission.repository;

import com.nexushr.auth.permission.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByRoleName(String roleName);
}