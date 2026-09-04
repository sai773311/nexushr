package com.nexushr.auth.password.controller;

import com.nexushr.auth.password.model.PasswordResetToken;
import com.nexushr.auth.password.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/password")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(
            PasswordResetService passwordResetService) {

        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(
            @RequestParam Long userId) {

        PasswordResetToken token =
                passwordResetService.createToken(userId);

        /*
         * Later this token will be sent
         * to the user's registered email.
         */

        return ResponseEntity.ok(
                "Password reset token created: "
                        + token.getToken()
        );
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyToken(
            @RequestParam String token) {

        passwordResetService.verifyToken(token);

        return ResponseEntity.ok(
                "Reset token is valid"
        );
    }
}