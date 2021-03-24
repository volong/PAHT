package com.paht.repository;

import com.paht.model.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

    @Query(value = "SELECT * FROM articles ORDER BY article_id OFFSET :pageIndex  LIMIT :pageSize ", nativeQuery = true)
    List<Article> findAll(
            @Param("pageSize") Long pageSize, @Param("pageIndex") Long pageIndex);

    @Query(value = "SELECT * FROM articles WHERE status_id = 2 OR status_id = 3 ORDER BY article_id asc LIMIT 9  OFFSET :pageIndex  ", nativeQuery = true )
    List<Article> findArticlesIsChecked(@Param("pageIndex") Long pageIndex);

    @Query(value = "SELECT * FROM articles WHERE article_id = :article_id", nativeQuery = true)
    Optional<Article> findById(@Param("article_id") Long article_id);

    @Query(value = "SELECT * FROM articles WHERE status_id = :status_id AND is_delete = 0 ORDER BY article_id asc LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Article> findByStatus_idSortASC(@Param("status_id") Long status_id, @Param("pageSize") Long pageSize, @Param("pageIndex") Long pageIndex);

    @Modifying
    @Query(value = "UPDATE articles SET title = :title, avatar = :avatar, status_id = :status_id, field_id = :field_id WHERE article_id = :article_id",
            nativeQuery = true)
    void update(@Param("title") String title,
                @Param("avatar") String avatar, @Param("status_id") Long status_id,
                 @Param("field_id") Long field_id, @Param("article_id") Long article_id);

    @Query(value = "SELECT  SUM(status_id) FROM articles WHERE status_id = :status_id and is_delete = 0", nativeQuery = true)
    Long sum(@Param("status_id") Long status_id);

    @Query(value = "SELECT * FROM articles WHERE status_id = :status_id AND is_delete = 0 AND CONCAT(title, content, dateofpost, location) LIKE %:keyword% ", nativeQuery = true)
    List<Article> searchByKeyword(@Param("status_id") Long status_id, @Param("keyword") String keyword);

}
