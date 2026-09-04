package com.nexushr.auth.refresh.service;

import com.nexushr.auth.refresh.model.RefreshToken;
import com.nexushr.auth.refresh.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final long refreshTokenDuration =
            7L * 24 * 60 * 60 * 1000;

    public RefreshTokenService(
            RefreshTokenRepository refreshTokenRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(Long userId) {

        refreshTokenRepository.deleteByUserId(userId);

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUserId(userId);

        refreshToken.setToken(
                UUID.randomUUID().toString()
        );

        refreshToken.setExpiryDate(
                Instant.now().plusMillis(
                        refreshTokenDuration
                )
        );

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(
            RefreshToken refreshToken) {

        if (refreshToken.getExpiryDate()
                .isBefore(Instant.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new RuntimeException(
                    "Refresh token has expired"
            );
        }

        return refreshToken;
    }

    public RefreshToken getByToken(String token) {

        return refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Refresh token not found"
                        ));
    }

        public void revoke(Long userId) {
                refreshTokenRepository.deleteByUserId(userId);
        }
}