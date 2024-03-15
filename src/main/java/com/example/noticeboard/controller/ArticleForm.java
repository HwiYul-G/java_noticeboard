package com.example.noticeboard.controller;

import com.example.noticeboard.domain.Article;

public record ArticleForm(String title, String writer, String password, String content) {

    public Article toEntity(){
        return new Article(title, content, writer, password);
    }
}
