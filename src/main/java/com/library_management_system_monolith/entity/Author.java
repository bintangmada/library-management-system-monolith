package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    private String name;

    private String biography;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    // Getters and Setters
    // ...
}
