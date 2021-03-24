package com.paht.model;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fb_id;

    private String content;

    private String dateoffb;


    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;


    public Feedback() {
    }

    public Feedback(Long fb_id, String content, String dateoffb, Article article) {
        this.fb_id = fb_id;
        this.content = content;
        this.dateoffb = dateoffb;
        this.article = article;
    }


    public Long getFb_id() {
        return fb_id;
    }

    public void setFb_id(Long fb_id) {
        this.fb_id = fb_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateoffb() {
        return dateoffb;
    }

    public void setDateoffb(String dateoffb) {
        this.dateoffb = dateoffb;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }



}
