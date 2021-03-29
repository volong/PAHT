package com.paht.service.impl;

import com.paht.model.Feedback;
import com.paht.repository.FeedbackRepository;
import com.paht.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Optional<Feedback> findByArticle_id(Long article_id) {
        return feedbackRepository.findByArticle_id(article_id);
    }

    @Override
    public void create(String content, Long article_id) {
        feedbackRepository.create(content, article_id);
    }

    @Override
    public void update(String content, String dateoffb, String respondent, Long article_id) {
        feedbackRepository.update(content, dateoffb, respondent, article_id);
    }
}
