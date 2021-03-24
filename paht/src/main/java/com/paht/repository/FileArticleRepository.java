package com.paht.repository;

import com.paht.model.FileArticle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileArticleRepository extends PagingAndSortingRepository<FileArticle, Long> {

//    @Query(value = "INSERT INTO files_article (uid, url, type, name, status, article_id) VALUES (:uid, :url, :type, :name, :status, :article_id)", nativeQuery = true)
//    void create(@Param("uid") String uid, @Param("url") String url, @Param("type") String type,
//                @Param("name") String name, @P("status") String status, @Param("article_id") Long article_id);

    @Query(value = "SELECT * FROM files_article WHERE article_id = :article_id", nativeQuery = true)
     List<FileArticle> findByArticle_id(@Param("article_id") Long article_id);
}
