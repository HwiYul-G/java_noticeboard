package com.example.noticeboard;

import com.example.noticeboard.aop.TimeTraceAop;
import com.example.noticeboard.repository.ArticleRepository;
import com.example.noticeboard.repository.JdbcTemplateArticleRepository;
import com.example.noticeboard.repository.JpaArticleRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final ArticleRepository articleRepository;

    @Autowired
    public SpringConfig(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

//    @Bean
//    public ArticleRepository articleRepository(){
////        return new JpaArticleRepository(em);
//        return new
//    }

}
