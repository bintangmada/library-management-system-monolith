package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "LOANS")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "user_id")
    private Integer userId; // Foreign key ke USERS

    @Column(name = "book_id")
    private Integer bookId; // Foreign key ke BOOKS

    @Column(name = "loan_date")
    private Date loanDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    // Getters and Setters
    // ...
}
