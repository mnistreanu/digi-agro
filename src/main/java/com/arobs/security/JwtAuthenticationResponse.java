package com.arobs.security;

import java.util.List;

public class JwtAuthenticationResponse {

    private final String token;
    private List<String> authorities;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public JwtAuthenticationResponse(String token, List<String> authorities) {
        this.token = token;
        this.authorities = authorities;
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
}
