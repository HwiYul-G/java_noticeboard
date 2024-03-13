package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class JpaArticleRepository implements ArticleRepository {

    private final EntityManager em;

    public JpaArticleRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Article save(Article article) {
        LocalDateTime now = LocalDateTime.now();
        article.setCreatedAt(now);
        article.setUpdatedAt(now);
        em.persist(article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        Article article = em.find(Article.class, id);
        return Optional.ofNullable(article);
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        List<Article> result = em.createQuery("select a from Article as a where a.title = :title", Article.class)
                .setParameter("title", title)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Article> findByWriter(String writer) {
        return em.createQuery("select a from Article as a where a.writer = :writer", Article.class)
                .setParameter("writer", writer)
                .getResultList();
    }

    @Override
    public List<Article> findByCreatedAt(LocalDate createdAt) {
        LocalDateTime startOfDay = createdAt.atStartOfDay();
        LocalDateTime endOfDay = createdAt.atTime(LocalTime.MAX);
        return em.createQuery("select a from Article as a where a.createdAt >= :startOfDay and a.createdAt <= :endOfDay", Article.class)
                .setParameter("startOfDay", startOfDay)
                .setParameter("endOfDay", endOfDay)
                .getResultList();
    }

    @Override
    public List<Article> findAll() {
        return em.createQuery("select a from Article as a", Article.class).getResultList();
    }
}
