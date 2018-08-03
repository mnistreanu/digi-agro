package com.arobs.repository;

import com.arobs.entity.Forecast;
import com.arobs.entity.ForecastHarvest;
import com.arobs.entity.ForecastParcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastHarvestRepository extends JpaRepository<ForecastHarvest, Long> {

    @Query("SELECT fh FROM ForecastHarvest fh " +
            "WHERE fh.forecastSnapshotId = :forecastSnapshotId " +
            "ORDER BY fh.createdAt " )
    List<ForecastHarvest> find(@Param("forecastSnapshotId") Long forecastSnapshotId);

//    @Modifying
//    @Query("DELETE FROM Forecast r WHERE r.id = :id")
//    void remove(@Param("id") Long id);
//
//    @Modifying
//    @Query("UPDATE Forecast f SET f.name = :name, f.description = :description WHERE f.id = :id ")
//    void update(@Param("id") Long id, @Param("name") String name, @Param("description") String description);
}

