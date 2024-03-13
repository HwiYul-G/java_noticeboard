package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SpringDataJpaArticleRepository extends JpaRepository<Article, Long>, ArticleRepository {

    @Override
    Optional<Article> findByTitle(String title);

    @Override
    List<Article> findByWriter(String writer);

    @Query("select a from Article a where a.createdAt >= :startOfDay and a.createdAt <= :endOfDay")
    List<Article> findByCreatedAt(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Override
    default List<Article> findByCreatedAt(LocalDate createdAt) {
        LocalDateTime startOfDay = createdAt.atStartOfDay();
        LocalDateTime endOfDay = createdAt.atTime(LocalTime.MAX);
        return findByCreatedAt(startOfDay, endOfDay);
    }
}
