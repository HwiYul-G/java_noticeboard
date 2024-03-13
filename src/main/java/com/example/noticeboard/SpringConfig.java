package com.example.noticeboard;

import com.example.noticeboard.repository.ArticleRepository;
import com.example.noticeboard.repository.JdbcTemplateArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public ArticleRepository articleRepository(){
        return new JdbcTemplateArticleRepository(dataSource);
    }
}
