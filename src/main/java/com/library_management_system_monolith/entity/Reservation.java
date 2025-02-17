package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer reservationId;

    @Column(name = "user_id")
    private Integer userId; // Foreign key ke USERS

    @Column(name = "book_id")
    private Integer bookId; // Foreign key ke BOOKS

    @Column(name = "reservation_date")
    private Timestamp reservationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    // Getters and Setters
    // ...
}
