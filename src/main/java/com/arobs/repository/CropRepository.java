package com.arobs.repository;

import com.arobs.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Integer> {

    @Query("SELECT c FROM Crop c " +
            "ORDER BY c.nameRo ")
    List<Crop> find();

    @Query("SELECT c FROM Crop c " +
            "WHERE c.cropCategoryId = :categoryId " +
            "ORDER BY c.nameRo ")
    List<Crop> find(@Param("categoryId") Integer categoryId);
}

