package com.arobs.repository;

import com.arobs.entity.FertilizerApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FertilizerApplicationRepository extends JpaRepository<FertilizerApplication, Long> {

    @Query(" SELECT fa FROM FertilizerApplication fa " +
            "JOIN FETCH fa.parcel " +
            "JOIN FETCH fa.fertilizer " +
            "WHERE fa.parcel.id = :parcelId " +
            "ORDER BY fa.applicationDate ")
    List<FertilizerApplication> find(@Param("parcelId") Long parcelId);

    @Modifying
    @Query("DELETE FROM FertilizerApplication fa WHERE fa.id = :id")
    void delete(@Param("id") Long id);

}

