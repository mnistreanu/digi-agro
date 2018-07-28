package com.arobs.security;

import com.arobs.model.ListItemModel;

import java.util.List;

public class JwtAuthenticationResponse {

    private String username;
    private String token;
    private List<String> authorities;
    private String logoUrl;
    private String language;
    private List<ListItemModel> tenants;

    public JwtAuthenticationResponse(String username, String token,
                                     List<String> authorities,
                                     String logoUrl, String language,
                                     List<ListItemModel> tenants) {
        this.username = username;
        this.token = token;
        this.authorities = authorities;
        this.logoUrl = logoUrl;
        this.language = language;
        this.tenants = tenants;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<ListItemModel> getTenants() {
        return tenants;
    }

    public void setTenants(List<ListItemModel> tenants) {
        this.tenants = tenants;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
