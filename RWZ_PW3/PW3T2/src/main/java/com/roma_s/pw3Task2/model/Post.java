package com.georgij.pw3Task2.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Title should contain only letters and digits")
    private String title;
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Content should contain only letters and digits")
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
