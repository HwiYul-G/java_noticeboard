package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaCommentRepository extends JpaRepository<Long, Comment>, CommentRepository{
}
