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

    private String respondent;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private User user;

    public Feedback() {
    }

    public Feedback(Long fb_id, String content, String dateoffb, String respondent, Article article, User user) {
        this.fb_id = fb_id;
        this.content = content;
        this.dateoffb = dateoffb;
        this.respondent = respondent;
        this.article = article;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRespondent() {
        return respondent;
    }

    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }
}
