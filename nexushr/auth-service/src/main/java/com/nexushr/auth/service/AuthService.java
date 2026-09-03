package com.nexushr.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexushr.auth.model.Role;
import com.nexushr.auth.model.User;
import com.nexushr.auth.repository.UserRepository;
import com.nexushr.auth.security.JwtService;
import com.nexushr.auth.refresh.model.RefreshToken;
import com.nexushr.auth.refresh.service.RefreshTokenService;
import com.nexushr.common.dto.RegisterRequest;
import com.nexushr.common.dto.JwtResponse;
import java.time.Instant;
import com.nexushr.auth.history.model.LoginHistory;
import com.nexushr.auth.history.repository.LoginHistoryRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final LoginHistoryRepository loginHistoryRepository;

    public AuthService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtService jwtService,
            RefreshTokenService refreshTokenService,
            LoginHistoryRepository loginHistoryRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.loginHistoryRepository = loginHistoryRepository;
    }

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        String requestedRole = request.getRole() == null ? "EMPLOYEE" : request.getRole();
        user.setRole(Role.valueOf(requestedRole.toUpperCase()));

        userRepository.save(user);
    }

    public JwtResponse login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!user.isActive()) throw new RuntimeException("Account is inactive");
        if (user.isLocked() && (user.getLockedUntil() == null || user.getLockedUntil().isAfter(Instant.now()))) {
            throw new RuntimeException("Account is locked");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            recordLogin(user, false);
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            if (user.getFailedLoginAttempts() >= 5) {
                user.setLocked(true);
                user.setLockedUntil(Instant.now().plusSeconds(15 * 60));
            }
            userRepository.save(user);
            throw new RuntimeException("Invalid Password");
        }

        user.setFailedLoginAttempts(0);
        user.setLocked(false);
        user.setLockedUntil(null);
        userRepository.save(user);
        recordLogin(user, true);

        String accessToken = jwtService.generateToken(user.getEmail(), user.getRole().name());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return new JwtResponse(accessToken, refreshToken.getToken(), user.getRole().name());
    }

    public void changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) throw new RuntimeException("Invalid current password");
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public void resetPassword(String token, String newPassword,
            com.nexushr.auth.password.service.PasswordResetService passwordResetService) {
        var resetToken = passwordResetService.verifyToken(token);
        User user = userRepository.findById(resetToken.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setFailedLoginAttempts(0);
        user.setLocked(false);
        user.setLockedUntil(null);
        userRepository.save(user);
        passwordResetService.deleteToken(resetToken);
    }

    public void logout(Long userId) {
        refreshTokenService.revoke(userId);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateAccount(String email, String fullName) {
        User user = getUser(email);
        user.setFullName(fullName);
        userRepository.save(user);
    }

    public void setActive(Long userId, boolean active) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(active);
        userRepository.save(user);
    }

    public void setLocked(Long userId, boolean locked) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setLocked(locked);
        user.setLockedUntil(locked ? Instant.now().plusSeconds(15 * 60) : null);
        userRepository.save(user);
    }

    private void recordLogin(User user, boolean successful) {
        LoginHistory history = new LoginHistory();
        history.setUserId(user.getId());
        history.setEmail(user.getEmail());
        history.setSuccessful(successful);
        history.setLoggedInAt(Instant.now());
        loginHistoryRepository.save(history);
    }

    public java.util.List<LoginHistory> loginHistory(String email) {
        return loginHistoryRepository.findByEmailOrderByLoggedInAtDesc(email);
    }
}