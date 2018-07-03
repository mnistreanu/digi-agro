package com.arobs.repository;

import com.arobs.entity.Authority;
import com.arobs.enums.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findAllByName(AuthorityName name);
}
