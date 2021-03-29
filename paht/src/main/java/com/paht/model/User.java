package com.paht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private String avatar;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Long is_enable;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Article> articles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Feedback> feedbacks;

    public User() {
    }

    public User(String fullname, String avatar, String username, String email, String password, Long is_enable) {
        this.fullname = fullname;
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_enable = is_enable;
    }

    public User(Long id, String fullname, String avatar, String username, String email, String password, Long is_enable) {
        this.id = id;
        this.fullname = fullname;
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_enable = is_enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getIs_enable() {
        return is_enable;
    }

    public void setIs_enable(Long is_enable) {
        this.is_enable = is_enable;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
