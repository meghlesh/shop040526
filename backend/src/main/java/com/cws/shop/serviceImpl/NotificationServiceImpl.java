package com.cws.shop.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cws.shop.dto.response.NotificationResponseDto;
import com.cws.shop.exception.NotficationNotFoundException;
import com.cws.shop.exception.UnauthorizedActionException;
import com.cws.shop.model.Notification;
import com.cws.shop.model.NotificationType;
import com.cws.shop.model.Role;
import com.cws.shop.model.User;
import com.cws.shop.repository.NotificationRepository;
import com.cws.shop.repository.UserRepository;
import com.cws.shop.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    // create  noification and save in the db.
    @Override
    public void sendNotification(User user, String title, String message, NotificationType type) {

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    //Get latest notifications notification
    @Override
    public List<NotificationResponseDto> getUserNotifications(Long userId) {

        return notificationRepository
                .findTop4ByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // all the notifications for that particular user
    @Override
    public List<NotificationResponseDto> getAllUserNotifications(Long userId) {

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public long getUnreadCount(Long userId) {
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

   @Override
    public void markAsRead(Long notificationId, Long userId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotficationNotFoundException("Notification not found"));

        // Ensure correct user
        if (!notification.getUser().getId().equals(userId)) {
            throw new UnauthorizedActionException("Unauthorized access");
        }

        
        if (!notification.isRead()) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
        else {
        	throw new NotificationAlreadyReadException("notification Already read");
        }
    }

// map response with dto
    private NotificationResponseDto mapToDto(Notification n) {

        NotificationResponseDto dto = new NotificationResponseDto();
        
        dto.setId(n.getId());
        dto.setTitle(n.getTitle());
        dto.setMessage(n.getMessage());
        dto.setCreatedAt(n.getCreatedAt());
        dto.setRead(n.isRead());

        return dto;
    }
    
    @Override
    public void notifyAdmins(String title, String message, NotificationType type) {

        List<User> admins = userRepository.findByRoleIn(
            List.of(Role.ADMIN)
        );

        for (User admin : admins) {
            sendNotification(admin, title, message, type);
        }
    }
    
    @Override
    public List<NotificationResponseDto> getUnreadNotifications(Long userId) {

        return notificationRepository
                .findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    
    
}