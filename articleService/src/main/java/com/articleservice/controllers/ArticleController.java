package com.articleservice.controllers;

import com.articleservice.domain.models.entities.ArticleEntity;
import com.articleservice.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @QueryMapping
    public List<ArticleEntity> getAllArticles() {
        return articleService.getAllArticles();
    }

    @QueryMapping
    public ArticleEntity getArticle(@Argument final Long id) {
        return articleService.getArticle(id);
    }

    @MutationMapping
    public ArticleEntity createArticle(@Argument final String title, @Argument final String content) {
        return articleService.createArticle(title, content);
    }

    @MutationMapping
    public ArticleEntity updateArticle(@Argument final long id, @Argument final String title, @Argument final String content) {
        return articleService.updateArticle(id, title, content);
    }

    @MutationMapping
    public Integer deleteArticle(@Argument final long id) {
        articleService.deleteArticle(id);
        return null;
    }
}
