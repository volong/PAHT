package com.paht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    private String status_name;

    private String status_color;

    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private Set<Article> articleSet;

    public Status() {
    }

    public Status(Long status_id, String status_name, String status_color, Set<Article> articleSet) {
        this.status_id = status_id;
        this.status_name = status_name;
        this.articleSet = articleSet;
        this.status_color = status_color;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }

    public String getStatus_color() {
        return status_color;
    }

    public void setStatus_color(String status_color) {
        this.status_color = status_color;
    }
}
