package com.arobs.security;

import java.util.List;

public class JwtAuthenticationResponse {

    private final String token;
    private List<String> authorities;
    private String logoUrl;

    public JwtAuthenticationResponse(String token, List<String> authorities, String logoUrl) {
        this.token = token;
        this.authorities = authorities;
        this.logoUrl = logoUrl;
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
}
