package com.arobs.repository;

import com.arobs.entity.MapEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapEventRepository extends JpaRepository<MapEvent, Long> {


    @Modifying
    @Query("UPDATE MapEvent e SET e.active = false WHERE e.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT e FROM MapEvent e " +
            " WHERE e.active = true AND e.userAccount.username = :username AND e.machine.identifier = :machineIdentifier " +
            " ORDER BY e.id")
    List<MapEvent> findByMachineIdentifierAndUsername(@Param("machineIdentifier") String machineIdentifier,
                                                       @Param("username") String username);
}
