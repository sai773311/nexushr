package com.nexushr.auth.refresh.controller;

import com.nexushr.auth.refresh.model.RefreshToken;
import com.nexushr.auth.refresh.service.RefreshTokenService;
import com.nexushr.auth.model.User;
import com.nexushr.auth.repository.UserRepository;
import com.nexushr.auth.security.JwtService;
import com.nexushr.common.dto.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/refresh")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public RefreshTokenController(
            RefreshTokenService refreshTokenService,
            UserRepository userRepository,
            JwtService jwtService) {

        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<JwtResponse> refreshToken(
            @RequestParam String token) {

        RefreshToken refreshToken =
                refreshTokenService.getByToken(token);

        refreshTokenService.verifyExpiration(
                refreshToken
        );

        User user = userRepository.findById(refreshToken.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(new JwtResponse(
            jwtService.generateToken(user.getEmail(), user.getRole().name()),
            refreshToken.getToken(),
            user.getRole().name()));
    }
}