package com.arobs.repository;

import com.arobs.entity.crop.CropSubculture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropSubcultureRepository extends JpaRepository<CropSubculture, Long> {

    @Query("SELECT s FROM CropSubculture s " +
            "ORDER BY s.nameRo ")
    List<CropSubculture> find();

    @Query("SELECT s FROM CropSubculture s " +
            "WHERE s.crop.id = :cropId " +
            "ORDER BY s.nameRo ")
    List<CropSubculture> find(@Param("cropId") Long cropId);

}

