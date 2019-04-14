package com.arobs.repository;

import com.arobs.entity.EventType;
import com.arobs.entity.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

    @Query("SELECT et FROM EventType et " +
            "WHERE et.tenantId = :tenantId AND et.active = true")
    List<EventType> find(@Param("tenantId") Long tenantId);

    @Query("SELECT et FROM EventType et " +
            "WHERE et.tenantId = :tenantId AND et.parent.id IS null AND et.active = true")
    List<EventType> findRoots(@Param("tenantId") Long tenantId);

    @Modifying
    @Query("UPDATE EventType et SET et.active = false WHERE et.id = :id")
    void remove(@Param("id") Long id);
    
}

