package com.paht.service;

import com.paht.model.Article;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<Article> findAll(@Param("pageSize") Long pageSize, @Param("pageIndex") Long pageIndex);

    List<Article> findArticlesIsChecked(@Param("pageIndex") Long pageIndex);

    List<Article> findByStatus_idSortASC(@Param("status_id") Long status_id, @Param("pageSize") Long pageSize, @Param("pageIndex") Long pageIndex);

    List<Article> searchByKeyword(@Param("status_id") Long status_id, @Param("keyword") String keyword);

    Optional<Article> findById(@Param("article_id") Long article_id);

    Long sum(@Param("status_id") Long status_id);
//    void create(@Param("content") String content, @Param("location") String location, @Param("dateofpost") String dateofpost);

    void save(@RequestBody Article article);

    void update(@Param("title") String title,
                @Param("avatar") String avatar, @Param("status_id") Long status_id,
                @Param("field_id") Long field_id, @Param("article_id") Long article_id);

}


