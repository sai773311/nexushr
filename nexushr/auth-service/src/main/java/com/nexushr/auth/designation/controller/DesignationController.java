package com.nexushr.auth.designation.controller;

import com.nexushr.auth.designation.model.Designation;
import com.nexushr.auth.designation.service.DesignationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/designations")
public class DesignationController {

    private final DesignationService designationService;

    public DesignationController(
            DesignationService designationService) {

        this.designationService = designationService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Designation createDesignation(
            @RequestBody Designation designation) {

        return designationService.createDesignation(designation);
    }

    @GetMapping
    public List<Designation> getAllDesignations() {

        return designationService.getAllDesignations();
    }

    @GetMapping("/{id}")
    public Optional<Designation> getDesignationById(
            @PathVariable Long id) {

        return designationService.getDesignationById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public Designation updateDesignation(
            @PathVariable Long id,
            @RequestBody Designation designation) {

        designation.setId(id);

        return designationService.updateDesignation(designation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public String deleteDesignation(
            @PathVariable Long id) {

        designationService.deleteDesignation(id);

        return "Designation Deleted Successfully";
    }
}