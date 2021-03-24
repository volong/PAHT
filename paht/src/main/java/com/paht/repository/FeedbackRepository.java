package com.paht.repository;

import com.paht.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query(value = "SELECT * FROM feedbacks WHERE article_id = :article_id", nativeQuery = true)
    Optional<Feedback> findByArticle_id(@Param("article_id") Long article_id);

    @Modifying
    @Query(value = "INSERT INTO feedbacks (article_id) VALUES (:article_id)", nativeQuery = true)
    void create(@Param("article_id") Long article_id);

    @Modifying
    @Query(value = "UPDATE feedbacks SET content = :content, dateoffb = :dateoffb WHERE article_id = :article_id", nativeQuery = true)
    void update(@Param("content") String content, @Param("dateoffb") String dateoffb, @Param("article_id") Long article_id);
}
