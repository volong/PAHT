package com.paht.service;

import com.paht.model.Feedback;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface FeedbackService {
    Optional<Feedback> findByArticle_id(@Param("article_id") Long article_id);

    void create(@Param("content") String content, @Param("article_id") Long article_id);

    void update(@Param("content") String content, @Param("dateoffb") String dateoffb, @Param("respondent") String respondent,@Param("article_id") Long article_id);
}
