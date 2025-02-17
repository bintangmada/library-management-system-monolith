package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    private String title;

    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear; // Menggunakan Integer untuk tahun

    private String isbn;

    @Column(name = "category_id")
    private Integer categoryId; // Foreign key ke CATEGORIES

    @Column(name = "total_copies")
    private Integer totalCopies;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Getters and Setters
    // ...
}
