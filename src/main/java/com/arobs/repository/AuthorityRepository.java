package com.arobs.repository;

import com.arobs.entity.Authority;
import com.arobs.enums.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("SELECT a FROM Authority a WHERE a.name = :name")
    List<Authority> find(@Param("name") AuthorityName name);
}
