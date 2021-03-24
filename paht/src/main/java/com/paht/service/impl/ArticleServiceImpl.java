package com.paht.service.impl;

import com.paht.model.Article;
import com.paht.repository.ArticleRepository;
import com.paht.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public List<Article> findAll(Long pageSize, Long pageIndex) {
        return articleRepository.findAll(pageSize, pageIndex);
    }

    @Override
    public List<Article> findArticlesIsChecked(Long pageIndex) {
        return articleRepository.findArticlesIsChecked(pageIndex);
    }

    @Override
    public List<Article> findByStatus_idSortASC(Long status_id,Long pageSize, Long pageIndex) {
        return articleRepository.findByStatus_idSortASC(status_id, pageSize, pageIndex);
    }

    @Override
    public List<Article> searchByKeyword(Long status_id, String keyword) {
        return articleRepository.searchByKeyword(status_id, keyword);
    }


    @Override
    public Optional<Article> findById(Long article_id) {
        return articleRepository.findById(article_id);
    }

    @Override
    public Long sum(Long status_id) {
        return articleRepository.sum(status_id);
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void update(String title, String avatar, Long status_id, Long field_id, Long article_id) {
        articleRepository.update(title, avatar, status_id, field_id, article_id);
    }
}
