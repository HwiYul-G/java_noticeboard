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
        articleService.createArticle(form.toEntity());
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


    @PostMapping("/articles/validatePassword/{id}")
    public String update(@PathVariable Long id, String password, Model model){
        if(articleService.validatePassword(id, password)) {
            Article article = articleService.findOne(id).get();
            model.addAttribute("article", article);
            return "/articles/updateArticleForm";
        }
        return "redirect:/articles/{id}";
    }

    @PostMapping("/articles/update/{id}")
    public String _update(@PathVariable Long id, ArticleForm form){
        articleService.updateArticle(id, form.toEntity());
        return "redirect:/articles/{id}";
    }

    @PostMapping("/articles/delete/{id}")
    public String delete(@PathVariable Long id, String password){
        boolean result = articleService.validatePassword(id, password);
        if(result){
            articleService.deleteArticle(id);
            return "redirect:/articles";
        }
        return "redirect:/articles/{id}";
    }

}
