package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class MemoryArticleRepositoryTest {

    MemoryArticleRepository repository = new MemoryArticleRepository();

    @BeforeEach
    private void before(){
        Article article1 = new Article();
        article1.setTitle("제목1");
        article1.setContent("컨텐츠1");
        article1.setPassword("비밀번호1");
        article1.setWriter("작성자1");
        repository.save(article1);


        Article article2 = new Article();
        article2.setTitle("제목2");
        article2.setContent("컨텐츠2");
        article2.setPassword("비밀번호2");
        article2.setWriter("작성자2");
        repository.save(article2);
    }

    @AfterEach
    private void afterEach(){
        repository.clearStore();
    }

    @Test
    void whenNewArticleStoreaAndfindById_IsSame_thenTrue(){
        Article article = new Article();
        article.setTitle("테스트제목");
        article.setContent("테스트 콘텐츠");
        article.setPassword("비밀번호");
        article.setWriter("작성자");

        repository.save(article);

        Article result = repository.findById(article.getId()).get();
        Assertions.assertThat(result).isEqualTo(article);
    }

    @Test
    void findByTitle_whenFindingNonExistingArticle_isEmpty(){
        Optional<Article> result = repository.findByTitle("존재하지않는제목");
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void whenFindExistingArticle_findByWriter_isNotEmpty(){
        Optional<Article> result = repository.findByWriter("작성자2");
        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    void findByCreatedAt_true(){
        // TODO : 24-03-08이 들어오면 이것이 일치하는 것만 검색하고 싶어.
    }

    @Test
    void findAll_true(){
        int expectedSize = 2;
        int size = repository.findAll().size();
        Assertions.assertThat(size).isEqualTo(expectedSize);
    }


}