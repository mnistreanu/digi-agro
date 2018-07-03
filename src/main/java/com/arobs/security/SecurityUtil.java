package com.arobs.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {

    public static final String unauthorizedMessage = "Unauthorized";
    public static final String audience = "web";

    public static final String tokenPrefix = "Bearer ";

    public static final String header = "Authorization";
    public static final String secret = "mySecret";
    public static final int expiration = 604800;

    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
