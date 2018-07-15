package com.arobs.repository;

import com.arobs.entity.Brand;
import com.arobs.entity.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyRepository extends JpaRepository<County, String> {

    @Query("SELECT c FROM County c " +
            "WHERE c.countryId = :countryId")
    List<County> findByCountryId(@Param("countryId") String countryId);

    @Query("SELECT c FROM County c " +
            "WHERE c.countryId = :countryId " +
            "AND (c.nameRo LIKE :name OR c.nameRu LIKE :name)")
    List<County> findByNameAndCountryId(@Param("countryId") String countryId, @Param("name") String name);

}

