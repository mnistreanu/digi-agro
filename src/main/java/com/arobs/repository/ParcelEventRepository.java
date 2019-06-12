package com.arobs.repository;

import com.arobs.entity.parcel.ParcelEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelEventRepository extends JpaRepository<ParcelEvent, Long> {

    @Query("SELECT e FROM ParcelEvent e " +
            "WHERE e.parcelId = :parcelId " +
            "ORDER BY e.date ASC")
    List<ParcelEvent> find(@Param("parcelId") Long parcelId);
}

