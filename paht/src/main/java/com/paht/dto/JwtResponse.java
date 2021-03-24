package com.paht.dto;


import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String fullname;
    private String avatar;
    private String username;
    private String email;
    private Long is_enable;
    private List<String> roles;


    public JwtResponse(String acessToken, Long id, String fullname, String avatar, String username,
                       String email, Long is_enable, List<String> roles) {
        this.token = acessToken;
        this.id = id;
        this.fullname = fullname;
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.is_enable = is_enable;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
