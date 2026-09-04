package com.nexushr.auth.designation.service;

import com.nexushr.auth.designation.model.Designation;
import com.nexushr.auth.designation.repository.DesignationRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationService {

    private final DesignationRepository designationRepository;

    public DesignationService(
            DesignationRepository designationRepository) {

        this.designationRepository = designationRepository;
    }

    public Designation createDesignation(
            Designation designation) {

        return designationRepository.save(designation);
    }

    public List<Designation> getAllDesignations() {

        return designationRepository.findAll();
    }

    public Optional<Designation> getDesignationById(
            Long id) {

        return designationRepository.findById(id);
    }

    public Designation updateDesignation(
            Designation designation) {

        return designationRepository.save(designation);
    }

    public void deleteDesignation(
            Long id) {

        designationRepository.deleteById(id);
    }
}