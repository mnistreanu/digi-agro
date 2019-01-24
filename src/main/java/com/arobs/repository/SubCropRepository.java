package com.arobs.repository;

import com.arobs.entity.CropVariety;
import com.arobs.entity.SubCrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCropRepository extends JpaRepository<SubCrop, Long> {

    @Query("SELECT s FROM SubCrop s " +
            "WHERE s.crop.id = :cropId " +
            "ORDER BY s.nameRo ")
    List<SubCrop> find(@Param("cropId") Long cropId);

}

