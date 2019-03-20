package com.arobs.repository;

import com.arobs.entity.CropSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropSeasonRepository extends JpaRepository<CropSeason, Long> {

    @Query("SELECT s FROM CropSeason s " +
            "WHERE s.tenantId = :tenantId " +
            "ORDER BY s.harvestYear ")
    List<CropSeason> find(@Param("tenantId") Long tenantId);


    @Query("SELECT DISTINCT s.harvestYear " +
            "FROM CropSeason s " +
            "WHERE s.tenantId = :tenantId " +
            "ORDER BY s.harvestYear ")
    List<Integer> getYears(@Param("tenantId") Long tenantId);
}

