package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findByArticleId(Long articleId);
    Optional<Comment> findById(Long id);
    void deleteById(Long id);
}
