package com.arobs.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(SecurityUtil.header);

        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith(SecurityUtil.tokenPrefix)) {
            token = jwtTokenUtil.getTokenFromHeader(requestHeader);

            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            }
            catch (IllegalArgumentException ex) {
                logger.error("an error occurred during getting username from token", ex);
            }
            catch (ExpiredJwtException ex) {
                logger.warn("the token is expired and not valid anymore", ex);
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        String tenantStr = request.getHeader("TENANT");
        if (tenantStr != null) {
            Long tenantId = Long.parseLong(tenantStr);
            request.getSession().setAttribute("tenant", tenantId);
        }

        filterChain.doFilter(request, response);
    }
}
