package com.paht.model;

import javax.persistence.*;

@Entity
@Table(name = "files_feedback")
public class FileFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String url;

    private String type;

    private String name;

    private String status;

    @ManyToOne
    @JoinColumn(name = "fb_id")
    private Feedback feedback;

    public FileFeedback() {
    }

    public FileFeedback(Long id, String uid, String url, String type, String name, String status, Feedback feedback) {
        this.id = id;
        this.uid = uid;
        this.url = url;
        this.type = type;
        this.name = name;
        this.status = status;
        this.feedback = feedback;
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

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
