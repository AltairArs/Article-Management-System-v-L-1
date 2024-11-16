package com.articleservice.services;

import com.articleservice.domain.models.entities.ArticleEntity;

import java.util.List;

public interface ArticleService {
    public abstract List<ArticleEntity> getAllArticles();
    public abstract ArticleEntity getArticle(long id);
    public abstract ArticleEntity createArticle(String title, String content);
    public abstract ArticleEntity updateArticle(long id, String title, String content);
    public abstract void deleteArticle(long id);
}
