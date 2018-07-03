package com.arobs.repository;

import com.arobs.entity.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {


    @Query("SELECT t FROM Telemetry t WHERE t.active = true ORDER BY t.id")
    List<Telemetry> findAll();

    @Modifying
    @Query("UPDATE Telemetry t SET t.active = false WHERE t.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT t FROM Telemetry t " +
            " WHERE t.active = true AND t.userAccount.username = :username AND t.machine.identifier = :machineIdentifier " +
            " ORDER BY t.id")
    List<Telemetry> findByMachineIdentifierAndUsername(@Param("machineIdentifier") String machineIdentifier,
                                                       @Param("username") String username);
}
