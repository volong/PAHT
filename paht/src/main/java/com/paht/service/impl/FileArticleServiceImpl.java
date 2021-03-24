package com.paht.service.impl;

import com.paht.model.FileArticle;
import com.paht.repository.FileArticleRepository;
import com.paht.service.FileArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FileArticleServiceImpl implements FileArticleService {

    @Autowired
    private FileArticleRepository articleRepository;

    @Override
    public List<FileArticle> findByArticle_id(Long article_id) {
        return articleRepository.findByArticle_id(article_id);
    }

    @Override
    public void save(FileArticle article) {
        articleRepository.save(article);
    }
}
