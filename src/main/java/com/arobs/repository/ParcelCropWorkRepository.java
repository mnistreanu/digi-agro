package com.arobs.repository;

import com.arobs.entity.ParcelCropWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelCropWorkRepository extends JpaRepository<ParcelCropWork, Long> {

}

