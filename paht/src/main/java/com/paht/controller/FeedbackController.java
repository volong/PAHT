package com.paht.controller;

import com.paht.model.Feedback;
import com.paht.service.impl.ArticleServiceImpl;
import com.paht.service.impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackServiceImpl feedbackService;

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/feedback/byarticle/{article_id}")
    public Optional<Feedback> findByArticle_id(@PathVariable("article_id") Long article_id) {
        return feedbackService.findByArticle_id(article_id);
    }

}
