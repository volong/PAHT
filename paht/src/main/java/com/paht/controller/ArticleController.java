package com.paht.controller;

import com.paht.model.Article;
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

    // Find All Articles By status_id
    @GetMapping("/articles/asc/{status_id}/{pageSize}/{pageIndex}")
    public List<Article> findByStatus_idSortASC(@PathVariable("status_id") Long status_id, @PathVariable("pageSize")
            Long pageSize, @PathVariable("pageIndex") Long pageIndex) {
        return articleService.findByStatus_idSortASC(status_id, pageSize, pageIndex);
    }

    // Find All Articles By status_id, dep_id
    @GetMapping("/articles/bydep/{status_id}/{dep_id}/{pageSize}/{pageIndex}")
    public List<Article> findByDep_id(@PathVariable("status_id") Long status_id, @PathVariable("dep_id") Long dep_id, @PathVariable("pageSize")
            Long pageSize, @PathVariable("pageIndex") Long pageIndex) {
        return articleService.findByDep_id(status_id, dep_id, pageSize, pageIndex);
    }

    @GetMapping("/search/{status_id}/{keyword}")
    public List<Article> searchByKeyword(@PathVariable("status_id") Long status_id, @PathVariable("keyword") String keyword) {
        return articleService.searchByKeyword(status_id, keyword);

    }

    @GetMapping("article/checked/{pageIndex}")
    public List<Article> findArticlesIsChecked(@PathVariable("pageIndex") Long pageIndex)  {
        return articleService.findArticlesIsChecked(pageIndex);
    }

    @GetMapping("articles/isdeleted/{pageIndex}")
    public List<Article> findArticlesIsDeleted(@PathVariable("pageIndex") Long pageIndex) {
        return articleService.findArticlesIsDeleted(pageIndex);
    }


    @GetMapping("/article/{article_id}")
    public Optional<Article> findById (@PathVariable("article_id") Long article_id) {
        return articleService.findById(article_id);
    }

    @GetMapping("/sum/articles/{status_id}")
    public Long getSumArticles(@PathVariable("status_id") Long status_id) {
        return articleService.sum(status_id);
    }

    @GetMapping("/sum/articles/fordep/{dep_id}/{status_id}")
    public Long getSumArticlesForDep(@PathVariable("dep_id") Long dep_id, @PathVariable("status_id") Long status_id) {
        return articleService.sumForDep(dep_id, status_id);
    }


    @PostMapping("/article")
    Long create(@RequestBody Article article){
        articleService.save(article);
        String content = "Đã tiếp nhận và chuyển thông tin phản ánh cho Trung tâm dịch vụ công xử lý.";
        feedbackService.create(content ,article.getArticle_id());
        return article.getArticle_id();
    }

    @PutMapping("/article")
   void update( @Param("title") String title,
                @Param("status_id") Long status_id,
                @Param("field_id") Long field_id, @Param("content") String content, @Param("dateoffb") String dateoffb,
                @Param("respondent") String respondent, @Param("dep_id") Long dep_id, @Param("article_id") Long article_id) {
    articleService.update(title, status_id, field_id, dep_id, article_id);
    feedbackService.update(content, dateoffb, respondent, article_id);

    }

    @DeleteMapping("/article/{article_id}")
    void delete(@PathVariable("article_id") Long article_id) {
        articleService.delete(article_id);
    }

    @PutMapping("/article/restore/{article_id}")
    void restore(@PathVariable("article_id") Long article_id) {
        articleService.restore(article_id);
    }

}
