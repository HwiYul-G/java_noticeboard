package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    void deleteById(Long id);
}
