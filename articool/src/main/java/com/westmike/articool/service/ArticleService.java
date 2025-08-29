package com.westmike.articool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westmike.articool.model.Article;
import com.westmike.articool.repository.ArticleRepository;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    // Example method to create and save an article
    public Article createArticle(Article.ArticleBuilder builder) {

        // Save to database and return the saved entity (with ID)
        return articleRepository.save(builder.build());
    }
}