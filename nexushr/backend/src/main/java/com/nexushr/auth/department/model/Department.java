package com.nexushr.auth.department.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departmentName;

    private String description;
    private Long parentDepartmentId;
    private Long departmentHeadId;
    private java.math.BigDecimal budget;

    @Enumerated(EnumType.STRING)
    private DepartmentStatus status;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DepartmentStatus getStatus() {
        return status;
    }

    public void setStatus(DepartmentStatus status) {
        this.status = status;
    }

    public Long getParentDepartmentId() { return parentDepartmentId; }
    public void setParentDepartmentId(Long value) { parentDepartmentId = value; }
    public Long getDepartmentHeadId() { return departmentHeadId; }
    public void setDepartmentHeadId(Long value) { departmentHeadId = value; }
    public java.math.BigDecimal getBudget() { return budget; }
    public void setBudget(java.math.BigDecimal value) { budget = value; }
}