package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BOOK_AUTHORS")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Field surrogate untuk primary key

    @Column(name = "book_id")
    private Integer bookId;  // Foreign key ke BOOKS

    @Column(name = "author_id")
    private Integer authorId;  // Foreign key ke AUTHORS

    // Getters and Setters
    // ...
}
