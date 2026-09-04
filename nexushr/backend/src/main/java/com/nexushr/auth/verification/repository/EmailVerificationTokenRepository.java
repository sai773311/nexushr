package com.nexushr.auth.verification.repository;

import com.nexushr.auth.verification.model.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationTokenRepository
        extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(
            String token);

    void deleteByUserId(Long userId);
}