package com.arobs.repository;

import com.arobs.entity.MachineGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineGroupRepository extends JpaRepository<MachineGroup, Long> {

    @Modifying
    @Query("DELETE FROM MachineGroup mg WHERE mg.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT mg FROM MachineGroup mg " +
            "WHERE mg.tenantId = :tenantId")
    List<MachineGroup> find(@Param("tenantId") Long tenantId);
}
