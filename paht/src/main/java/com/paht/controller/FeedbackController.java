package com.paht.controller;

import com.paht.model.Feedback;
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

    @GetMapping("/feedback/byarticle/{article_id}")
    public Optional<Feedback> findByArticle_id(@PathVariable("article_id") Long article_id) {
        return feedbackService.findByArticle_id(article_id);
    }

//    @PostMapping("/feedback")
//    public void save(@RequestBody Feedback feedback) {
//        feedbackService.create(feedback);
//    }

    @PutMapping("/feedback")
    public void update(@Param("content") String content, @Param("dateoffb") String dateoffb, @Param("article_id") Long article_id) {
        feedbackService.update(content, dateoffb, article_id);
    }
}
