package com.arobs.repository;

import com.arobs.entity.CropCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropCategoryRepository extends JpaRepository<CropCategory, Integer> {

    @Query("SELECT cc FROM CropCategory cc ")
    List<CropCategory> find();
}

