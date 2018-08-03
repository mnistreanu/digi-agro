package com.arobs.repository;

import com.arobs.entity.Forecast;
import com.arobs.entity.ForecastParcel;
import com.arobs.entity.ForecastSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastParcelRepository extends JpaRepository<ForecastParcel, Long> {

    @Query("SELECT fp FROM ForecastParcel fp " +
            "WHERE fp.forecastSnapshotId = :forecastSnapshotId " )
    List<ForecastParcel> find(@Param("forecastSnapshotId") Long forecastSnapshotId);

//    @Modifying
//    @Query("DELETE FROM Forecast r WHERE r.id = :id")
//    void remove(@Param("id") Long id);
//
//    @Modifying
//    @Query("UPDATE Forecast f SET f.name = :name, f.description = :description WHERE f.id = :id ")
//    void update(@Param("id") Long id, @Param("name") String name, @Param("description") String description);
}

