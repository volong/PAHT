package com.paht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article_id;

    private String title;

    private String content;

    private String avatar;

    private String dateofpost;

    private String location;

    private Long is_delete;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


   @ManyToOne
    @JoinColumn(name = "field_id")
   private Field field;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private Set<Feedback> feedbacks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private User dep;

    public Article() {
    }

    public Article(Long article_id, String title, String content, String avatar, String dateofpost, String location, Long is_delete, Status status, Field field, Set<Feedback> feedbacks, User user, User dep) {
        this.article_id = article_id;
        this.title = title;
        this.content = content;
        this.avatar = avatar;
        this.dateofpost = dateofpost;
        this.location = location;
        this.is_delete = is_delete;
        this.status = status;
        this.field = field;
        this.feedbacks = feedbacks;
        this.user = user;
        this.dep = dep;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateofpost() {
        return dateofpost;
    }

    public void setDateofpost(String dateofpost) {
        this.dateofpost = dateofpost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Long is_delete) {
        this.is_delete = is_delete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getDep() {
        return dep;
    }

    public void setDep(User dep) {
        this.dep = dep;
    }
}
