package com.arobs.repository;

import com.arobs.entity.crop.CropCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropCategoryRepository extends JpaRepository<CropCategory, Long> {

    @Query("SELECT cc FROM CropCategory cc ")
    List<CropCategory> find();
}

