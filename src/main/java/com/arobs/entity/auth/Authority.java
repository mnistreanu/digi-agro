package com.arobs.entity.auth;


import com.arobs.entity.BaseEntity;
import com.arobs.enums.AuthorityName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Authority extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    public Authority() {
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }
}
