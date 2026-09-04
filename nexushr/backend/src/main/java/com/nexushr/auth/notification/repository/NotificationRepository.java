package com.nexushr.auth.notification.repository;

import com.nexushr.auth.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByUserIdAndReadStatus(
            Long userId,
            boolean readStatus);
}