package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "user_id")
    private Integer userId; // Foreign key ke USERS

    @Column(name = "book_id")
    private Integer bookId; // Foreign key ke BOOKS

    private Integer rating;

    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText;

    @Column(name = "review_date")
    private Timestamp reviewDate;

    // Getters and Setters
    // ...
}
