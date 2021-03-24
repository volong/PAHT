package com.paht.service;

import com.paht.model.FileArticle;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FileArticleService {

//    void create(@Param("uid") String uid, @Param("url") String url, @Param("type") String type,
//                @Param("name") String name, @P("status") String status, @Param("article_id") Long article_id);

    List<FileArticle> findByArticle_id(@Param("article_id") Long article_id);

    void save(@RequestBody FileArticle article);
}
