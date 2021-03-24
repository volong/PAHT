package com.paht.controller;

import com.paht.model.Article;
import com.paht.model.User;
import com.paht.service.impl.ArticleServiceImpl;
import com.paht.service.impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private FeedbackServiceImpl feedbackService;

    @GetMapping("/articles/{pageSize}/{pageIndex}")
    public List<Article> findAll(@PathVariable("pageSize") Long pageSize, @PathVariable("pageIndex") Long pageIndex) {
       return articleService.findAll(pageSize,pageIndex);
    }

    @GetMapping("/articles/asc/{status_id}/{pageSize}/{pageIndex}")
    public List<Article> findByStatus_idSortASC(@PathVariable("status_id") Long status_id, @PathVariable("pageSize")
            Long pageSize, @PathVariable("pageIndex") Long pageIndex) {
        return articleService.findByStatus_idSortASC(status_id, pageSize, pageIndex);
    }

    @GetMapping("/search/{status_id}/{keyword}")
    public List<Article> searchByKeyword(@PathVariable("status_id") Long status_id, @PathVariable("keyword") String keyword) {
        return articleService.searchByKeyword(status_id, keyword);

    }

    @GetMapping("article/checked/{pageIndex}")
    public List<Article> findArticlesIsChecked(@PathVariable("pageIndex") Long pageIndex)  {
        return articleService.findArticlesIsChecked(pageIndex);
    }


    @GetMapping("/article/{article_id}")
    public Optional<Article> findById (@PathVariable("article_id") Long article_id) {
        return articleService.findById(article_id);
    }

    @GetMapping("/sum/articles/{status_id}")
    public Long getSumArticles(@PathVariable("status_id") Long status_id) {
        return articleService.sum(status_id);
    }
//
//    @PostMapping("/article")
//    void create(Article article,@Param("content") String content, @Param("location") String location, @Param("dateofpost") String dateofpost){
//        articleService.create(content, location, dateofpost);
//    }

    @PostMapping("/article")
    Long create(@RequestBody Article article){
        articleService.save(article);
        feedbackService.create(article.getArticle_id());
        return article.getArticle_id();
    }

    @PutMapping("/article")
   void update( @Param("title") String title,
                @Param("avatar") String avatar, @Param("status_id") Long status_id,
                @Param("field_id") Long field_id, @Param("article_id") Long article_id) {
    articleService.update(title, avatar, status_id, field_id,article_id);

    }


}
