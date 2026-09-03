package com.nexushr.auth.verification.controller;

import com.nexushr.auth.verification.model.EmailVerificationToken;
import com.nexushr.auth.verification.service.EmailVerificationService;
import com.nexushr.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/verification")
public class EmailVerificationController {

    private final EmailVerificationService verificationService;
        private final UserRepository userRepository;

    public EmailVerificationController(
                        EmailVerificationService verificationService,
                        UserRepository userRepository) {

        this.verificationService = verificationService;
                this.userRepository = userRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendVerification(
            @RequestParam Long userId) {

        EmailVerificationToken token =
                verificationService.createToken(userId);

        /*
         * Later this token will be sent
         * through the user's email.
         */

        return ResponseEntity.ok(
                "Verification token created: "
                        + token.getToken()
        );
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(
            @RequestParam String token) {

        EmailVerificationToken verificationToken =
                verificationService.verifyToken(token);

                userRepository.findById(verificationToken.getUserId()).ifPresent(user -> {
                        user.setEmailVerified(true);
                        userRepository.save(user);
                });

        verificationService.deleteToken(
                verificationToken);

        return ResponseEntity.ok(
                "Email verified successfully"
        );
    }
}