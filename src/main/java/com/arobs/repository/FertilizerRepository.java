package com.arobs.repository;

import com.arobs.entity.Fertilizer;
import com.arobs.enums.FertilizerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

    @Query("SELECT f FROM Fertilizer f " +
            "ORDER BY f.nameRo ")
    List<Fertilizer> find();

    @Query("SELECT f FROM Fertilizer f " +
            "WHERE f.fertilizerType = :fertilizerType " +
            "ORDER BY f.nameRo ")
    List<Fertilizer> find(@Param("fertilizerType") FertilizerType fertilizerType);

    @Modifying
    @Query("UPDATE Fertilizer f SET f.deletedAt = NOW() WHERE f.id = :id")
    void remove(@Param("id") Long id);

}

