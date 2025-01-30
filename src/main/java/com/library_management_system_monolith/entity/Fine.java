package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fines")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal amount;
    private LocalDateTime fineDate;
    private boolean isPaid;
    private LocalDateTime paidDate;
    
    @OneToOne
    @JoinColumn(name = "borrowing_id")
    private Borrowing borrowing;
    
    // Getter dan Setter
    public Fine() {
    }

    public Fine(BigDecimal amount, LocalDateTime fineDate, boolean isPaid, LocalDateTime paidDate) {
        this.amount = amount;
        this.fineDate = fineDate;
        this.isPaid = isPaid;
        this.paidDate = paidDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getFineDate() {
        return fineDate;
    }

    public void setFineDate(LocalDateTime fineDate) {
        this.fineDate = fineDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDateTime getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDateTime paidDate) {
        this.paidDate = paidDate;
    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Borrowing borrowing) {
        this.borrowing = borrowing;
    }
}
