package com.example.noticeboard.controller;

import com.example.noticeboard.domain.Article;
import com.example.noticeboard.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @PutMapping("/articles/{id}")
    public String update(@PathVariable Long id, String password){
        // TODO : update 관련 로직
        if(articleService.validatePassword(id, password)) {
            return "/articles/update/{id}"; // TODO : 이 링크에 들어가서 변경 사항을 save한다.
        }
        return "/articles/{id}";
    }

    @DeleteMapping("/articles/{id}")
    public String delete(@PathVariable Long id, String password){
        boolean result = articleService.validatePassword(id, password);
        if(result){
            articleService.deleteArticle(id);
            return "/articles";
        }
        return "/articles/{id}";
    }

}
