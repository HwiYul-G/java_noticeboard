package com.example.noticeboard.service;

import com.example.noticeboard.controller.dto.CommentDto;
import com.example.noticeboard.domain.Comment;
import com.example.noticeboard.repository.ArticleRepository;
import com.example.noticeboard.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,ArticleRepository articleRepository){
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public List<CommentDto> comments(Long articleId){
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    public CommentDto create(Long articleId, CommentDto dto){
        if(articleRepository.findById(articleId).isPresent()){
            Comment comment = Comment.createComment(dto, articleId);
            Comment created = commentRepository.save(comment);
            return CommentDto.createCommentDto(created);
        }
        throw new IllegalArgumentException("대상 게시글이 없습니다. 댓글을 생성할 수 없어요.");
    }

    public CommentDto update(Long id, CommentDto dto){
        Comment target = commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        target.patch(dto);
        Comment updated = commentRepository.save(target);
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id){
        Comment target = commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        commentRepository.deleteById(id);
        return CommentDto.createCommentDto(target);
    }


}
