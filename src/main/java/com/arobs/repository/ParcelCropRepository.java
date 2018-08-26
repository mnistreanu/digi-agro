package com.arobs.repository;

import com.arobs.entity.ParcelCrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ParcelCropRepository extends JpaRepository<ParcelCrop, Long> {

    @Query("SELECT pc FROM ParcelCrop pc " +
            "WHERE pc.parcelId = :parcelId AND pc.plantedAt = :plantedDate AND pc.deletedAt IS null")
    ParcelCrop find(@Param("parcelId") Long parcelId, @Param("plantedDate") Date plantedDate);

    @Query("SELECT MAX(pc.plantedAt) FROM ParcelCrop pc " +
            "WHERE pc.parcelId = :parcelId AND pc.deletedAt IS null")
    Date getLastPlantedDate(@Param("parcelId") Long parcelId);

}

