package com.arobs.repository;

import com.arobs.entity.forecast.ForecastHarvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastHarvestRepository extends JpaRepository<ForecastHarvest, Long> {

    @Query("SELECT fh FROM ForecastHarvest fh " +
            "WHERE fh.forecastSnapshotId = :forecastSnapshotId " +
            "ORDER BY fh.createdAt ")
    List<ForecastHarvest> find(@Param("forecastSnapshotId") Long forecastSnapshotId);
}

