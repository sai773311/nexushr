package com.nexushr.auth.controller;

import com.nexushr.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserAdminController {
    private final AuthService authService;
    public UserAdminController(AuthService authService) { this.authService = authService; }

    @PatchMapping("/{id}/activation")
    public ResponseEntity<String> activation(@PathVariable Long id, @RequestParam boolean active) {
        authService.setActive(id, active);
        return ResponseEntity.ok("Account status updated");
    }

    @PatchMapping("/{id}/lock")
    public ResponseEntity<String> lock(@PathVariable Long id, @RequestParam boolean locked) {
        authService.setLocked(id, locked);
        return ResponseEntity.ok("Account lock status updated");
    }
}