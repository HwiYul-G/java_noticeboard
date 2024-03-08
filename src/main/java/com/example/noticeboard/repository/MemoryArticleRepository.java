package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryArticleRepository implements ArticleRepository{
    // 공유되는 동시성 문제 때문에 HashMap이 아니라 ConcurrenthashMap을 사용한다.
    private static Map<Long, Article> store = new ConcurrentHashMap<>();
    private static long sequence = 0L; // id 값을 표시하기 위함.

    @Override
    public Article save(Article article) {
        article.setId(++sequence);
        LocalDateTime now = LocalDateTime.now();
        article.setCreatedAt(now);
        article.setUpdatedAt(now);
        store.put(article.getId(), article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        return store.values().stream()
                .filter(article -> article.getTitle().equals(title))
                .findAny();
    }

    @Override
    public Optional<Article> findByWriter(String writer) {
        return store.values().stream()
                .filter(article -> article.getWriter().equals(writer))
                .findAny();
    }

    // TODO : 24-03-08이 들어오면 이것이 일치하는 것만 검색하고 싶어.
    @Override
    public Optional<Article> findByCreatedAt(LocalDateTime createdAt) {
        return store.values().stream()
                .filter(article -> article.getCreatedAt().equals(createdAt))
                .findAny();
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
