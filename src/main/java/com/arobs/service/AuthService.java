package com.arobs.service;


import com.arobs.enums.AuthorityName;
import com.arobs.security.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public JwtUser getCurrentUser() {
        return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isSuperAdmin() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(AuthorityName.ROLE_SUPER_ADMIN.name())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(AuthorityName.ROLE_ADMIN.name())) {
                return true;
            }
        }
        return false;
    }
}
