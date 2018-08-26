package com.arobs.repository;

import com.arobs.entity.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT f FROM Forecast f " +
            "WHERE f.tenantId = :tenantId " +
            "AND f.deletedAt IS NULL " +
            "ORDER BY f.harvestingYear ")
    List<Forecast> findActive(@Param("tenantId") Long tenantId);

    @Query("SELECT f FROM Forecast f " +
            "WHERE f.tenantId = :tenantId " +
            "ORDER BY f.harvestingYear ")
    List<Forecast> findAll(@Param("tenantId") Long tenantId);

    @Modifying
    @Query("UPDATE Forecast f SET f.name = :name, f.description = :description WHERE f.id = :id ")
    void update(@Param("id") Long id, @Param("name") String name, @Param("description") String description);

    @Modifying
    @Query("UPDATE Forecast f SET f.deletedAt = :now, f.deletedBy = :userId WHERE f.id = :id")
    void remove(@Param("id") Long id, @Param("userId") Long userId, @Param("now") Date now);
}

