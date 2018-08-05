package com.arobs.repository;

import com.arobs.entity.ParcelGeometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelGeometryRepository extends JpaRepository<ParcelGeometry, Long> {

}

