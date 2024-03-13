package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateArticleRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", article.getTitle());
        parameters.put("writer", article.getWriter());
        parameters.put("password", article.getPassword());
        parameters.put("content", article.getContent());
        parameters.put("createdAt", article.getCreatedAt());
        parameters.put("updatedAt", article.getUpdatedAt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setId(key.longValue());
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        List<Article> result = jdbcTemplate.query("select * from article where id = ?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        List<Article> result = jdbcTemplate.query("select * from article where title = ?", articleRowMapper(), title);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findByWriter(String writer) {
        return jdbcTemplate.query("select * from article writer = ?", articleRowMapper(), writer);
    }

    @Override
    public List<Article> findByCreatedAt(LocalDate createdAt) {
        return jdbcTemplate.query("select * from article createdAt = ?", articleRowMapper(), createdAt);
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    private RowMapper<Article> articleRowMapper(){
        return new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setTitle(rs.getString("title"));
                article.setWriter(rs.getString("writer"));
                article.setPassword(rs.getString("password"));
                article.setContent(rs.getString("content"));
                return article;
            }
        };
    }

}
