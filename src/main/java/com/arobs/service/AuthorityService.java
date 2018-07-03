package com.arobs.service;

import com.arobs.entity.Authority;
import com.arobs.enums.AuthorityName;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService implements HasRepository<AuthorityRepository> {

    @Autowired
    private AuthorityRepository authorityRepository;

    List<Authority> findAllByName(AuthorityName name) {
        return getRepository().findAllByName(name);
    }

    @Override
    public AuthorityRepository getRepository() {
        return authorityRepository;
    }
}
