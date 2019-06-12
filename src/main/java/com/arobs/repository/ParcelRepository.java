package com.arobs.repository;

import com.arobs.entity.parcel.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    @Query("SELECT p FROM Parcel p WHERE p.tenantId = :tenantId AND p.active = true")
    List<Parcel> find(@Param("tenantId") Long tenantId);

    @Modifying
    @Query("UPDATE Parcel p SET p.active = false " +
            "WHERE p.id = :id")
    void softDelete(@Param("id") Long id);

}

