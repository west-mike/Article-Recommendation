package com.westmike.articool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.westmike.articool.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Spring Data JPA provides all basic CRUD operations
}