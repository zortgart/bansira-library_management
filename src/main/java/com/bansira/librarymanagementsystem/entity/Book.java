package com.bansira.librarymanagementsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "books")
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "genre")
    private String genre;

    @Column(name = "publicationYear")
    private int publicationYear;

    @Column(name = "availability")
    private boolean availability;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
