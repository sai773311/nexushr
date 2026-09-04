package com.nexushr.auth.controller;

import com.nexushr.auth.service.AuthService;
import com.nexushr.common.dto.RegisterRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nexushr.common.dto.LoginRequest;
import com.nexushr.common.dto.JwtResponse;
import com.nexushr.auth.password.service.PasswordResetService;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    public AuthController(AuthService authService, PasswordResetService passwordResetService) {
        this.authService = authService;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User Registered Successfully");
    }

   @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
        @RequestBody LoginRequest request) {

    JwtResponse result = authService.login(
            request.getEmail(),
            request.getPassword());

    return ResponseEntity.ok(result);
 }

 @PostMapping("/logout")
 public ResponseEntity<String> logout(Authentication authentication) {
     authService.logout(authService.getUser(authentication.getName()).getId());
     return ResponseEntity.ok("Logged out successfully");
 }

 @PostMapping("/password/change")
 public ResponseEntity<String> changePassword(Authentication authentication, @RequestParam String currentPassword, @RequestParam String newPassword) {
     authService.changePassword(authentication.getName(), currentPassword, newPassword);
     return ResponseEntity.ok("Password changed successfully");
 }

 @PostMapping("/password/reset")
 public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
     authService.resetPassword(token, newPassword, passwordResetService);
     return ResponseEntity.ok("Password reset successfully");
 }
}