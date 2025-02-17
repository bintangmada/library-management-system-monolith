package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "USER_LOGS")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "user_id")
    private Integer userId; // Foreign key ke USERS

    private String activity;

    @Column(name = "activity_date")
    private Timestamp activityDate;

    // Getters and Setters
    // ...
}
