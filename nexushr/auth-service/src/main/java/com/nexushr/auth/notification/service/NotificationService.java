package com.nexushr.auth.notification.service;

import com.nexushr.auth.notification.model.Notification;
import com.nexushr.auth.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(
            NotificationRepository notificationRepository) {

        this.notificationRepository = notificationRepository;
    }

    // Create notification
    public Notification createNotification(Notification notification) {

        return notificationRepository.save(notification);
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {

        return notificationRepository.findAll();
    }

    // Get notification by ID
    public Notification getNotificationById(Long id) {

        return notificationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification not found"));
    }

    // Get notifications for a user
    public List<Notification> getUserNotifications(Long userId) {

        return notificationRepository.findByUserId(userId);
    }

    // Get unread notifications
    public List<Notification> getUnreadNotifications(Long userId) {

        return notificationRepository
                .findByUserIdAndReadStatus(userId, false);
    }

    // Mark notification as read
    public Notification markAsRead(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"));

        notification.setReadStatus(true);

        return notificationRepository.save(notification);
    }

    // Delete notification
    public void deleteNotification(Long id) {

        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException(
                    "Notification not found");
        }

        notificationRepository.deleteById(id);
    }
}