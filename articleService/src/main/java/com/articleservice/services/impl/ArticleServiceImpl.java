package com.articleservice.services.impl;

import com.articleservice.domain.models.entities.ArticleEntity;
import com.articleservice.repo.entities.ArticleRepository;
import com.articleservice.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public ArticleEntity getArticle(long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public ArticleEntity createArticle(String title, String content) {
        return articleRepository.save(ArticleEntity.builder()
                .content(content)
                .title(title)
                .build());
    }

    @Override
    public ArticleEntity updateArticle(long id, String title, String content) {
        ArticleEntity articleEntity = articleRepository.findById(id).get();
        if (content != null) articleEntity.setContent(content);
        if (title != null) articleEntity.setTitle(title);
        return articleRepository.save(articleEntity);
    }

    @Override
    public void deleteArticle(long id) {
        articleRepository.delete(getArticle(id));
    }
}
