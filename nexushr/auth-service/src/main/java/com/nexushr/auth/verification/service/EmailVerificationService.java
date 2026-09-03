package com.nexushr.auth.verification.service;

import com.nexushr.auth.verification.model.EmailVerificationToken;
import com.nexushr.auth.verification.repository.EmailVerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;

    public EmailVerificationService(
            EmailVerificationTokenRepository tokenRepository) {

        this.tokenRepository = tokenRepository;
    }

    public EmailVerificationToken createToken(
            Long userId) {

        tokenRepository.deleteByUserId(userId);

        EmailVerificationToken token =
                new EmailVerificationToken();

        token.setUserId(userId);

        token.setToken(
                UUID.randomUUID().toString()
        );

        // Valid for 24 hours
        token.setExpiryDate(
                Instant.now().plusSeconds(24 * 60 * 60)
        );

        return tokenRepository.save(token);
    }

    public EmailVerificationToken verifyToken(
            String token) {

        EmailVerificationToken verificationToken =
                tokenRepository.findByToken(token)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid verification token"
                                ));

        if (verificationToken.getExpiryDate()
                .isBefore(Instant.now())) {

            tokenRepository.delete(
                    verificationToken);

            throw new RuntimeException(
                    "Verification token has expired"
            );
        }

        return verificationToken;
    }

    public void deleteToken(
            EmailVerificationToken token) {

        tokenRepository.delete(token);
    }
}