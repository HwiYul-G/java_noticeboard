package com.example.noticeboard.service;

import com.example.noticeboard.domain.Comment;
import com.example.noticeboard.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long createComment(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }

    public void delete(Long id, String password) {
        if (validatePassword(id, password)) {
            commentRepository.deleteById(id);
        }
    }

    private boolean validatePassword(Long id, String password) {
        Comment comment = commentRepository.findById(id).get();
        return comment.getPassword().equals(password);
    }

}
