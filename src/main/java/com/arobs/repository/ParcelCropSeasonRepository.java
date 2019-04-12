package com.arobs.repository;

import com.arobs.entity.ParcelCropSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParcelCropSeasonRepository extends JpaRepository<ParcelCropSeason, Long> {

    @Query("SELECT pcs FROM ParcelCropSeason pcs " +
            "JOIN FETCH pcs.cropSeason season " +
            "WHERE pcs.parcelId = :parcelId " +
            "ORDER BY season.harvestYear DESC ")
    List<ParcelCropSeason> find(@Param("parcelId") Long parcelId);

    @Query("SELECT pcs FROM ParcelCropSeason pcs " +
            "JOIN FETCH pcs.cropSeason season " +
            "WHERE pcs.parcelId = :parcelId " +
            "AND season.harvestYear = :harvestYear ")
    ParcelCropSeason find(@Param("parcelId") Long parcelId, @Param("harvestYear") int harvestYear);

    @Query("SELECT pcs FROM ParcelCropSeason pcs " +
            "JOIN FETCH pcs.cropSeason season " +
            "WHERE season.tenantId = :tenantId " +
            "AND season.harvestYear = :harvestYear " +
            "AND pcs.parcelId <> NULL " +
            "ORDER BY season.harvestYear DESC ")
    List<ParcelCropSeason> findByTenant(@Param("tenantId") Long tenantId, @Param("harvestYear") int harvestYear);


    //    @Query("SELECT MAX (season.harvestYear) FROM ParcelCropSeason pcs " +
//            "JOIN FETCH pcs.cropSeason season " +
//            "WHERE pcs.parcelId = :parcelId ")
//    long getLastHarvestYear(@Param("parcelId") Long parcelId);

}

