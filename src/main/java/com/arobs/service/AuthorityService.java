package com.arobs.service;

import com.arobs.entity.Authority;
import com.arobs.enums.AuthorityName;
import com.arobs.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService extends BaseEntityService<Authority, AuthorityRepository> {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public AuthorityRepository getRepository() {
        return authorityRepository;
    }

    List<Authority> find(AuthorityName name) {
        return getRepository().find(name);
    }
}
