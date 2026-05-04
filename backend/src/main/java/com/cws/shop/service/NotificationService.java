package com.cws.shop.service;

import java.util.List;

import com.cws.shop.dto.response.NotificationResponseDto;
import com.cws.shop.model.NotificationType;
import com.cws.shop.model.User;

public interface NotificationService {
	
	void sendNotification(User user, String title, String message, NotificationType type);

    // Get latest 4 notification for that.
    List<NotificationResponseDto> getUserNotifications(Long userId);

    // Get all notifications
    List<NotificationResponseDto> getAllUserNotifications(Long userId);

    // Get unread notification count
    long getUnreadCount(Long userId);
    

    void markAsRead(Long notificationId, Long userId);
    
    void notifyAdmins(String title, String message, NotificationType type);
    
    List<NotificationResponseDto> getUnreadNotifications(Long userId);

}
