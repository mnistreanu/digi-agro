package com.arobs.repository;

import com.arobs.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    @Query("SELECT p FROM Parcel p WHERE p.tenantId = :tenantId")
    List<Parcel> find(@Param("tenantId") Long tenantId);

}

