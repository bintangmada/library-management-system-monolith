package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "FINES")
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fine_id")
    private Integer fineId;

    @Column(name = "user_id")
    private Integer userId; // Foreign key ke USERS

    @Column(name = "loan_id")
    private Integer loanId; // Foreign key ke LOANS

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private FineStatus status;

    // Getters and Setters
    // ...
}
