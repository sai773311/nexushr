package com.nexushr.auth.training.controller;

import com.nexushr.auth.training.model.TrainingProgram;
import com.nexushr.auth.training.service.TrainingProgramService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingProgramController {
    private final TrainingProgramService service;
    public TrainingProgramController(TrainingProgramService service) { this.service = service; }
    @PostMapping @PreAuthorize("hasAnyRole('HR', 'MANAGER', 'ADMIN')") public TrainingProgram create(@RequestBody TrainingProgram item) { return service.create(item); }
    @GetMapping @PreAuthorize("hasAnyRole('HR', 'MANAGER', 'ADMIN')") public List<TrainingProgram> all() { return service.all(); }
    @GetMapping("/mine") @PreAuthorize("hasRole('EMPLOYEE')") public List<TrainingProgram> mine(Authentication authentication) { return service.mine(authentication.getName()); }
    @PutMapping("/{id}/assign") @PreAuthorize("hasAnyRole('HR', 'MANAGER', 'ADMIN')") public TrainingProgram assign(@PathVariable Long id, @RequestParam String email) { return service.assign(id, email); }
    @PutMapping("/{id}/progress") @PreAuthorize("hasRole('EMPLOYEE')") public TrainingProgram progress(@PathVariable Long id, @RequestParam Integer value, Authentication authentication) { return service.updateProgress(id, authentication.getName(), value); }
}