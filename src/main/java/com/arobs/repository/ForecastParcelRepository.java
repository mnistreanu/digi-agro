package com.arobs.repository;

import com.arobs.entity.forecast.ForecastParcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastParcelRepository extends JpaRepository<ForecastParcel, Long> {

    @Query("SELECT fp FROM ForecastParcel fp " +
            "WHERE fp.forecastSnapshotId = :snapshotId ")
    List<ForecastParcel> find(@Param("snapshotId") Long snapshotId);

    @Modifying
    @Query("DELETE FROM ForecastParcel fp WHERE fp.forecastSnapshotId = :snapshotId")
    void delete(@Param("snapshotId") Long snapshotId);
}

