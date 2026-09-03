package com.nexushr.auth.permission.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role_permissions", uniqueConstraints = @UniqueConstraint(columnNames = {"roleName", "permissionName"}))
public class RolePermission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String roleName;
    private String permissionName;
    private boolean enabled = true;
    public RolePermission() { }
    public RolePermission(String roleName, String permissionName, boolean enabled) { this.roleName = roleName; this.permissionName = permissionName; this.enabled = enabled; }
    public Long getId() { return id; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String value) { roleName = value; }
    public String getPermissionName() { return permissionName; }
    public void setPermissionName(String value) { permissionName = value; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean value) { enabled = value; }
}