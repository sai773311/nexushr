package com.nexushr.auth.password.service;

import com.nexushr.auth.password.model.PasswordResetToken;
import com.nexushr.auth.password.repository.PasswordResetTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;

    public PasswordResetService(
            PasswordResetTokenRepository tokenRepository) {

        this.tokenRepository = tokenRepository;
    }

    public PasswordResetToken createToken(Long userId) {

        tokenRepository.deleteByUserId(userId);

        PasswordResetToken token =
                new PasswordResetToken();

        token.setUserId(userId);

        token.setToken(
                UUID.randomUUID().toString()
        );

        // Token valid for 15 minutes
        token.setExpiryDate(
                Instant.now().plusSeconds(15 * 60)
        );

        return tokenRepository.save(token);
    }

    public PasswordResetToken verifyToken(
            String token) {

        PasswordResetToken resetToken =
                tokenRepository.findByToken(token)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid reset token"
                                ));

        if (resetToken.getExpiryDate()
                .isBefore(Instant.now())) {

            tokenRepository.delete(resetToken);

            throw new RuntimeException(
                    "Reset token has expired"
            );
        }

        return resetToken;
    }

    public void deleteToken(
            PasswordResetToken token) {

        tokenRepository.delete(token);
    }
}