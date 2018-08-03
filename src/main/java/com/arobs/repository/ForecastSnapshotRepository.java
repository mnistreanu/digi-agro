package com.arobs.repository;

import com.arobs.entity.Forecast;
import com.arobs.entity.ForecastSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastSnapshotRepository extends JpaRepository<ForecastSnapshot, Long> {

    @Query("SELECT fs FROM ForecastSnapshot fs " +
            "WHERE fs.forecastId = :forecastId " +
            "ORDER BY fs.createdAt ")
    List<ForecastSnapshot> find(@Param("forecastId") Long forecastId);

//    @Modifying
//    @Query("DELETE FROM Forecast r WHERE r.id = :id")
//    void remove(@Param("id") Long id);
//
//    @Modifying
//    @Query("UPDATE Forecast f SET f.name = :name, f.description = :description WHERE f.id = :id ")
//    void update(@Param("id") Long id, @Param("name") String name, @Param("description") String description);
}

