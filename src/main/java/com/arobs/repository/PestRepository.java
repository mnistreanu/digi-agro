package com.arobs.repository;

import com.arobs.entity.Pest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PestRepository extends JpaRepository<Pest, Long> {

    @Query("SELECT p FROM Pest p " +
            "ORDER BY p.nameRo ")
    List<Pest> find();

}

