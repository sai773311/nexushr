package com.nexushr.auth.controller;

import com.nexushr.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/account")
public class AccountController {
    private final AuthService authService;

    public AccountController(AuthService authService) { this.authService = authService; }

    @GetMapping
    public ResponseEntity<?> account(Authentication authentication) {
        var user = authService.getUser(authentication.getName());
        return ResponseEntity.ok(java.util.Map.of(
                "id", user.getId(),
                "fullName", user.getFullName(),
                "email", user.getEmail(),
                "role", user.getRole().name(),
                "active", user.isActive(),
                "emailVerified", user.isEmailVerified()));
    }

    @PutMapping
    public ResponseEntity<String> update(Authentication authentication, @RequestParam String fullName) {
        authService.updateAccount(authentication.getName(), fullName);
        return ResponseEntity.ok("Account updated successfully");
    }
}