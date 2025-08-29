package com.westmike.articool.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Define the Article entity/object type, which maps to the "articles" table in the database
// Use Lombok annotations to generate boilerplate code like getters, setters, constructors, etc.
@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String title;
    private String author;
    private String publishedDate;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(length = 200)
    private String categories;
    private String link;
}