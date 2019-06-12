package com.arobs.repository;

import com.arobs.entity.MachineTelemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineTelemetryRepository extends JpaRepository<MachineTelemetry, Long> {

    @Modifying
    @Query("UPDATE MachineTelemetry t SET t.active = false WHERE t.id = :id")
    void softDelete(@Param("id") Long id);

    @Query("SELECT t FROM MachineTelemetry t " +
            " WHERE t.active = true AND t.userAccount.id = :userId AND t.machine.id = :machineId " +
            " ORDER BY t.id")
    List<MachineTelemetry> find(@Param("machineId") Long machineId,
                                @Param("userId") Long userId);
}
