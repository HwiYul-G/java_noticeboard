package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findByArticleId(Long articleId);
    void deleteById(Long id);
}
