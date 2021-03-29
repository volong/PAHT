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


    @Query(value = "SELECT * FROM articles WHERE status_id = 2 OR status_id = 3 ORDER BY article_id DESC LIMIT 9  OFFSET :pageIndex  ", nativeQuery = true )
    List<Article> findArticlesIsChecked(@Param("pageIndex") Long pageIndex);

    @Query(value = "SELECT * FROM articles WHERE is_delete = 1 ORDER BY article_id DESC LIMIT 9  OFFSET :pageIndex", nativeQuery = true )
    List<Article> findArticlesIsDeleted(@Param("pageIndex") Long pageIndex);

    @Query(value = "SELECT * FROM articles WHERE article_id = :article_id", nativeQuery = true)
    Optional<Article> findById(@Param("article_id") Long article_id);

    @Query(value = "SELECT * FROM articles WHERE status_id = :status_id AND is_delete = 0 ORDER BY article_id asc LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Article> findByStatus_idSortASC(@Param("status_id") Long status_id, @Param("pageSize") Long pageSize, @Param("pageIndex") Long pageIndex);

    @Query(value = "SELECT * FROM articles WHERE status_id = :status_id AND is_delete = 0  AND dep_id = :dep_id ORDER BY article_id asc LIMIT :pageSize OFFSET :pageIndex", nativeQuery = true)
    List<Article> findByDep_id(@Param("status_id") Long status_id, @Param("dep_id") Long dep_id, @Param("pageSize") Long pageSize, @Param("pageIndex") Long pageIndex);

    @Modifying
    @Query(value = "UPDATE articles SET title = :title, status_id = :status_id, field_id = :field_id, dep_id = :dep_id WHERE article_id = :article_id",
            nativeQuery = true)
    void update(@Param("title") String title,
               @Param("status_id") Long status_id,
                 @Param("field_id") Long field_id, @Param("dep_id") Long dep_id, @Param("article_id") Long article_id);

    @Query(value = "SELECT count(status_id) FROM articles WHERE status_id = :status_id and is_delete = 0", nativeQuery = true)
    Long sum(@Param("status_id") Long status_id);

    @Query(value = "SELECT count(status_id) FROM articles WHERE dep_id = :dep_id AND status_id = :status_id and is_delete = 0", nativeQuery = true)
    Long sumForDep(@Param("dep_id") Long dep_id, @Param("status_id") Long status_id);

    @Query(value = "SELECT * FROM articles WHERE status_id = :status_id AND is_delete = 0 AND CONCAT(title, content, dateofpost, location) LIKE %:keyword% ", nativeQuery = true)
    List<Article> searchByKeyword(@Param("status_id") Long status_id, @Param("keyword") String keyword);

    @Modifying
    @Query(value = "UPDATE articles SET is_delete = 1 WHERE article_id = :article_id", nativeQuery = true)
    void delete(@Param("article_id") Long article_id);

    @Modifying
    @Query(value = "UPDATE articles SET is_delete = 1 WHERE article_id = :article_id",nativeQuery = true)
    void restore(@Param("article_id") Long article_id);
}
