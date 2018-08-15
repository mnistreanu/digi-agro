package com.arobs.service;

import com.arobs.entity.UserAccount;
import com.arobs.security.JwtUserFactory;
import com.arobs.weather.snapshot.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        
        if (userAccount == null) {
            throw new UsernameNotFoundException("No user found with username " + username);
        } 
        else {
            return JwtUserFactory.create(userAccount);
        }
    }
}
