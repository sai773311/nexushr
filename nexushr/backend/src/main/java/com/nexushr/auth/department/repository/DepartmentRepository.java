package com.nexushr.auth.department.repository;

import com.nexushr.auth.department.model.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
        java.util.List<Department> findByParentDepartmentId(Long parentDepartmentId);
}