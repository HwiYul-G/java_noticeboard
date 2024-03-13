package com.example.noticeboard.service;

import com.example.noticeboard.domain.Article;
import com.example.noticeboard.repository.ArticleRepository;
import com.example.noticeboard.repository.MemoryArticleRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ArticleServiceIntegrationTest {
    @Autowired ArticleService articleService;
    @Autowired ArticleRepository articleRepository;

    @BeforeEach
    private void beforeEach(){
        Article article1 = new Article();
        article1.setTitle("제목1");
        article1.setContent("컨텐츠1");
        article1.setPassword("비밀번호1");
        article1.setWriter("작성자1");
        articleService.createArticle(article1);


        Article article2 = new Article();
        article2.setTitle("제목2");
        article2.setContent("컨텐츠2");
        article2.setPassword("비밀번호2");
        article2.setWriter("작성자2");
        articleService.createArticle(article2);

        Article article3 = new Article();
        article3.setTitle("제목3");
        article3.setContent("컨텐츠3");
        article3.setPassword("비밀번호2");
        article3.setWriter("작성자2");
        articleService.createArticle(article3);
    }


    @Test
    void createArticle(){
        // given
        Article article = new Article();
        article.setTitle("테스트제목");
        article.setContent("테스트 콘텐츠");
        article.setPassword("비밀번호");
        article.setWriter("작성자");
        // when
        Long saveId = articleService.createArticle(article);
        // then
        Article foundArticle = articleService.findOne(saveId).get();
        Assertions.assertThat(foundArticle.getWriter()).isEqualTo(article.getWriter());
    }

    @Test
    void createArticle_duplicatedTitle_Exception(){
        //given
        Article article = new Article();
        article.setTitle("제목3");
        article.setWriter("저자");
        article.setPassword("비번");
        article.setContent("컨텐츠");

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> articleService.createArticle(article));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 제목입니다.");
    }

    @Test
    void findArticles_whenGetSize_isEqual(){
        int expected = 3;
        int result = articleService.findArticles().size();
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void findOne_whenNoneExistingArticle_IsEmpty(){
        Optional<Article> result = articleService.findOne("없는 제목");
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void findArticlesByWriter_whenGetSize_isEqualToTwo(){
        int expected = 2;
        int result = articleService.findArticlesByWriter("작성자2").size();
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void findArticlesByDate_whenGetSize_isEqualToOne(){
        int expected = 1;

        Article article = new Article();
        article.setTitle("테스트제목");
        article.setWriter("테스트저자");
        article.setPassword("테스트비번");
        article.setContent("테스트컨텐츠");
        Long id = articleService.createArticle(article);

        Article findArticle = articleService.findOne(id).get();
        findArticle.setCreatedAt(LocalDateTime.of(2023,3,8,11,13,20));

        int result = articleService.findArticlesByDate("2023-03-08").size();

        Assertions.assertThat(result).isEqualTo(expected);
    }

}