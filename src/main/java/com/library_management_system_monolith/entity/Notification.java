package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String message;
    private String type; // EMAIL, SMS, SYSTEM
    private LocalDateTime notificationDate;
    private boolean isRead;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    // Getter dan Setter
    public Notification() {
    }

    public Notification(String message, String type, LocalDateTime notificationDate, boolean isRead, User user) {
        this.message = message;
        this.type = type;
        this.notificationDate = notificationDate;
        this.isRead = isRead;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
