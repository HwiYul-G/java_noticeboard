package com.example.noticeboard.service;

import com.example.noticeboard.domain.Article;
import com.example.noticeboard.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long createArticle(Article article) {
        validateDuplicateTitle(article);
        articleRepository.save(article);
        return article.getId();
    }

    private void validateDuplicateTitle(Article article) {
        // Optional<>이 보이는 게 좋지 않아서 한줄로 변경
        articleRepository.findByTitle(article.getTitle())
                .ifPresent(a -> {
                    throw new IllegalStateException("이미 존재하는 제목입니다.");
                });
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> findOne(Long articleId) {
        return articleRepository.findById(articleId);
    }

    public Optional<Article> findOne(String title) {
        return articleRepository.findByTitle(title);
    }

    public List<Article> findArticlesByWriter(String writer) {
        return articleRepository.findByWriter(writer);
    }

    public List<Article> findArticlesByDate(String date) {
        LocalDate localDateTime = parseStringToLocalDateTime(date);
        return articleRepository.findByCreatedAt(localDateTime);
    }

    private LocalDate parseStringToLocalDateTime(String date) {
        return LocalDate.parse(date, formatter);
    }


    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public boolean validatePassword(Long id, String password) {
        Optional<Article> article = articleRepository.findById(id);
        return article.isPresent() && article.get().getPassword().equals(password);
    }

}
