package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "USER_CONFIRMATION_TOKENS")
public class UserConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirmation_id")
    private Integer confirmationId;

    @Column(name = "user_id")
    private Integer userId; // Foreign key ke USERS

    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "confirmation_type")
    private ConfirmationType confirmationType;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "expires_at")
    private Timestamp expiresAt;

    @Column(name = "is_used")
    private Boolean isUsed;

    public UserConfirmationToken() {
    }

    public Integer getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(Integer confirmationId) {
        this.confirmationId = confirmationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ConfirmationType getConfirmationType() {
        return confirmationType;
    }

    public void setConfirmationType(ConfirmationType confirmationType) {
        this.confirmationType = confirmationType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}
