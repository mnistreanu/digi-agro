package com.arobs.repository;

import com.arobs.entity.Crop;
import com.arobs.entity.CropVariety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropVarietyRepository extends JpaRepository<CropVariety, Long> {

    @Query("SELECT v FROM CropVariety v " +
            "WHERE v.cropId = :cropId " +
            "ORDER BY v.nameRo ")
    List<CropVariety> find(@Param("cropId") Long cropId);

}

