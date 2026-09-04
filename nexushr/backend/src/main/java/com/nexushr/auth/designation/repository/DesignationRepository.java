package com.nexushr.auth.designation.repository;

import com.nexushr.auth.designation.model.Designation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository
        extends JpaRepository<Designation, Long> {

}