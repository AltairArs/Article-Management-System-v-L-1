package com.articleservice.repo.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<com.storeservice.domain.models.entities.Article, Long> {
}