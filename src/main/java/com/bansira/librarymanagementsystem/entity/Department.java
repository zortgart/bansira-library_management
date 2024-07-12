package com.bansira.librarymanagementsystem.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "departments")
@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
