package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id); // Optional<>로 감싸면서 null인 경우 보호됨.
    Optional<Article> findByTitle(String title);
    List<Article> findByWriter(String writer);
    List<Article> findByCreatedAt(LocalDate createdAt);
    List<Article> findAll();

    void deleteById(Long id);
}
