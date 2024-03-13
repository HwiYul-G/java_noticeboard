package com.example.noticeboard;

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
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public ArticleRepository articleRepository(){
        return new JpaArticleRepository(em);
    }
}
