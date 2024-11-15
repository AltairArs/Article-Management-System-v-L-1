package com.articleservice.services.impl;

import com.articleservice.repo.entities.ArticleRepository;
import com.articleservice.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
}
