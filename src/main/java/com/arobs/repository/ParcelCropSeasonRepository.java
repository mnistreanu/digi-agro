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
            "WHERE pcs.parcelId = :parcelId " +
            "ORDER BY pcs.plantedAt DESC ")
    List<ParcelCropSeason> find(@Param("parcelId") Long parcelId);

    @Query("SELECT pcs FROM ParcelCropSeason pcs " +
            "WHERE pcs.parcelId = :parcelId AND pcs.cropSeason.harvestYear = :harvestYear ")
    ParcelCropSeason find(@Param("parcelId") Long parcelId, @Param("harvestYear") Integer harvestYear);

    @Query("SELECT MAX (pcs.plantedAt) FROM ParcelCropSeason pcs " +
            "WHERE pcs.parcelId = :parcelId ")
    Date getLastPlantedDate(@Param("parcelId") Long parcelId);

}

