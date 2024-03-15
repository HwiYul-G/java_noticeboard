package com.example.noticeboard.controller.dto;

import com.example.noticeboard.domain.Comment;

public record CommentDto(Long id, Long articleId, String writer, String content, String password) {

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticleId(),
                comment.getWriter(),
                comment.getContent(),
                comment.getPassword()
        );
    }
}
