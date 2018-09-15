package com.arobs.repository;

import com.arobs.entity.HarmfulOrganism;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarmfulOrganismRepository extends JpaRepository<HarmfulOrganism, Long> {

    @Query("SELECT ho FROM HarmfulOrganism ho " +
            "ORDER BY ho.nameRo ")
    List<HarmfulOrganism> find();

}

