package com.example.noticeboard.domain;

import com.example.noticeboard.controller.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long articleId; // 댓글의 부모 게시글
    private String writer;
    private String password;
    private String content; // 댓글 본문
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Comment() {
    }

    public Comment(Long articleId, String writer, String password, String content) {
        this.articleId = articleId;
        this.writer = writer;
        this.password = password;
        this.content = content;
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Comment createComment(CommentDto dto, Long articleId){
        return new Comment(articleId, dto.writer(), dto.password(), dto.content());
    }

    public void patch(CommentDto dto){
        this.writer = dto.writer();
        this.password = dto.password();
        this.content = dto.content();
        this.updatedAt = LocalDateTime.now();
    }

}
