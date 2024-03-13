package com.example.noticeboard.controller;

import com.example.noticeboard.domain.Article;
import com.example.noticeboard.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/new") // articles/new
    public String createForm() {
        return "articles/createArticleForm";
    }

    @PostMapping("/articles/new")
    public String create(ArticleForm form) {
        Article article = new Article();
        article.setTitle(form.title());
        article.setPassword(form.password());
        article.setWriter(form.writer());
        article.setContent(form.content());

        articleService.createArticle(article);

        return "redirect:/";
    }

    @GetMapping("/articles")
    public String list(Model model) {
        List<Article> articles = articleService.findArticles();
        model.addAttribute("articles", articles);
        return "articles/articleList";
    }

    @GetMapping("/articles/{id}")
    public String article(@PathVariable Long id, Model model) {
        Article article = articleService.findOne(id).get();
        model.addAttribute("article", article);
        return "articles/details";
    }


}
