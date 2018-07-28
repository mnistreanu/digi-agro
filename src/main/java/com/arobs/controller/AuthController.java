package com.arobs.controller;

import com.arobs.entity.UserAccount;
import com.arobs.model.ListItemModel;
import com.arobs.model.userAccount.UserAccountModel;
import com.arobs.security.JwtAuthenticationRequest;
import com.arobs.security.JwtAuthenticationResponse;
import com.arobs.security.JwtTokenUtil;
import com.arobs.security.JwtUser;
import com.arobs.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/current-ser", method = RequestMethod.GET)
    public ResponseEntity<UserAccountModel> getCurrentUser()  {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new UserAccountModel(userAccountService.findByUsername(user.getUsername())));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JwtAuthenticationResponse> auth(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }

        UserAccount userAccount = userAccountService.findByUsername(userDetails.getUsername());
        List<ListItemModel> tenants = userAccount.getTenants().stream()
                .map(t -> new ListItemModel(t.getId(), t.getName())).collect(Collectors.toList());

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(
                userAccount.getUsername(),
                token, authorities, userAccount.getSafeLogoUrl(),
                userAccount.getLanguage(), tenants);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/authorities", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAuthorities() {
        List<String> authorities = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }
        return ResponseEntity.ok(authorities);
    }

}
