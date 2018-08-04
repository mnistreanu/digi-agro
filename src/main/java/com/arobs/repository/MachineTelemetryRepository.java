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


    @Query("SELECT t FROM MachineTelemetry t WHERE t.active = true ORDER BY t.id")
    List<MachineTelemetry> findAll();

    @Modifying
    @Query("UPDATE MachineTelemetry t SET t.active = false WHERE t.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT t FROM MachineTelemetry t " +
            " WHERE t.active = true AND t.userAccount.id = :userId AND t.machine.identifier = :machineIdentifier " +
            " ORDER BY t.id")
    List<MachineTelemetry> find(@Param("machineIdentifier") String machineIdentifier,
                                @Param("userId") Long userId);
}
