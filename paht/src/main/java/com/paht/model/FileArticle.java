package com.paht.model;

import javax.persistence.*;

@Entity
@Table(name = "files_article")
public class FileArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String url;

    private String type;

    private String name;

    private String status;

    private Long article_id;

//    @ManyToOne
//    @JoinColumn(name = "article_id")
//    private Article article;

    public FileArticle() {
    }

    public FileArticle(Long id, String uid, String url, String type, String name, String status, Long article_id) {
        this.id = id;
        this.uid = uid;
        this.url = url;
        this.type = type;
        this.name = name;
        this.status = status;
        this.article_id = article_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }
}
