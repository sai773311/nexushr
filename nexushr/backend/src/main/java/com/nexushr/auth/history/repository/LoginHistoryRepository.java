package com.nexushr.auth.history.repository;

import com.nexushr.auth.history.model.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    List<LoginHistory> findByEmailOrderByLoggedInAtDesc(String email);
    long countBySuccessfulFalse();
}