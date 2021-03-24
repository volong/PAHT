package com.paht.controller;


import com.paht.model.FileArticle;
import com.paht.service.impl.FileArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FileArticleController {

    @Autowired
    private FileArticleServiceImpl fileArticleService;

    @PostMapping("/files_article")
    void save(@RequestBody List<FileArticle> fileArticleList) {
        for (FileArticle fileArticle : fileArticleList) {
            fileArticleService.save(fileArticle);
        }
    }

    @GetMapping("/files_article/{article_id}")
    public List<FileArticle> findByArticle_id(@PathVariable("article_id") Long article_id) {
        return fileArticleService.findByArticle_id(article_id);
    }

}
