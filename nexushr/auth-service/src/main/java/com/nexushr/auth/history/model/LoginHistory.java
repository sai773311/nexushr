package com.nexushr.auth.history.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "login_history")
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String email;
    private boolean successful;
    private Instant loggedInAt;

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isSuccessful() { return successful; }
    public void setSuccessful(boolean successful) { this.successful = successful; }
    public Instant getLoggedInAt() { return loggedInAt; }
    public void setLoggedInAt(Instant loggedInAt) { this.loggedInAt = loggedInAt; }
}