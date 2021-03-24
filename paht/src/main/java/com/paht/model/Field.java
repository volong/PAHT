package com.paht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long field_id;

    private String field_name;

    @Column(columnDefinition = "BIGINT default 0")
    private Long is_delete;

    @JsonIgnore
    @OneToMany(mappedBy = "field")
    private Set<Article> articleSet;

    public Field() {
    }

    public Field(Long field_id, String field_name, Long is_delete, Set<Article> articleSet) {
        this.field_id = field_id;
        this.field_name = field_name;
        this.is_delete = is_delete;
        this.articleSet = articleSet;
    }

    public Long getField_id() {
        return field_id;
    }

    public void setField_id(Long field_id) {
        this.field_id = field_id;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public Long getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Long is_delete) {
        this.is_delete = is_delete;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }
}
