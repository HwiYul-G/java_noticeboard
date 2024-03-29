package com.example.noticeboard.repository;

import com.example.noticeboard.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


class MemoryArticleRepositoryTest {

    MemoryArticleRepository repository = new MemoryArticleRepository();

    @BeforeEach
    private void beforeEach(){
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

        Article article3 = new Article();
        article3.setTitle("제목3");
        article3.setContent("컨텐츠3");
        article3.setPassword("비밀번호2");
        article3.setWriter("작성자2");
        repository.save(article3);
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
    void whenFindArticles_findByWriter_sizeIsTwo(){
        int expectedSize = 2;
        List<Article> result = repository.findByWriter("작성자2");
        Assertions.assertThat(result.size()).isEqualTo(expectedSize);
    }

    @Test
    void findByCreatedAt_true(){
        int expected = 1;

        Article article = new Article();
        article.setTitle("테스트제목");
        article.setContent("테스트 콘텐츠");
        article.setPassword("비밀번호");
        article.setWriter("작성자");

        Article savedArticle = repository.save(article);
        savedArticle.setCreatedAt(LocalDateTime.of(2023,3,8,11,11,11));

        LocalDate findDate = LocalDate.of(2023,3,8);
        int result = repository.findByCreatedAt(findDate).size();

        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    void findAll_true(){
        int expectedSize = 3;
        int size = repository.findAll().size();
        Assertions.assertThat(size).isEqualTo(expectedSize);
    }

    @Test
    void deleteById_createArticleAndDeleteById_totalSizeIsThree(){
        int expected = 3;

        Article article = new Article();
        article.setTitle("타이틀");
        article.setContent("컨텐츠");
        article.setPassword("12345");
        article.setWriter("작성자");
        repository.save(article);

        Long id = repository.findByTitle("타이틀").get().getId();
        repository.deleteById(id);

        int result = repository.findAll().size();
        Assertions.assertThat(result).isEqualTo(expected);

    }


}