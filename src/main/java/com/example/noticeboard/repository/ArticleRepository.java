package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id); // Optional<>로 감싸면서 null인 경우 보호됨.
    Optional<Article> findByTitle(String title);
    Optional<Article> findByWriter(String writer);
    Optional<Article> findByCreatedAt(LocalDateTime createdAt); // TODO : 24-03-08이 들어오면 이것이 일치하는 것만 검색하고 싶어.
    List<Article> findAll();
}
