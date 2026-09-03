package com.nexushr.auth.controller;

import com.nexushr.auth.history.model.LoginHistory;
import com.nexushr.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth/security")
public class SecurityController {
    private final AuthService authService;
    public SecurityController(AuthService authService) { this.authService = authService; }

    @GetMapping("/login-history")
    public ResponseEntity<List<LoginHistory>> loginHistory(Authentication authentication) {
        return ResponseEntity.ok(authService.loginHistory(authentication.getName()));
    }
}