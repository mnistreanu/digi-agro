package com.arobs.repository;

import com.arobs.entity.AgroTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgroTaskRepository extends JpaRepository<AgroTask, Long> {

    @Query("SELECT t FROM AgroTask t " +
            "WHERE t.tenantId = :tenantId " +
            "ORDER BY t.nameRo ")
    List<AgroTask> find(@Param("tenantId") Long tenantId);

    @Query("SELECT t FROM AgroTask t " +
            "WHERE t.tenantId = :tenantId " +
            "AND t.scheduledStart >= :scheduledTime " +
            "ORDER BY t.nameRo ")
    List<AgroTask> findInFuture(@Param("tenantId") Long tenantId, @Param("scheduledTime") Date scheduledTime);

    @Query("SELECT t FROM AgroTask t " +
            "WHERE t.tenantId = :tenantId " +
            "AND t.scheduledStart < :scheduledTime " +
            "ORDER BY t.nameRo ")
    List<AgroTask> findInPast(@Param("tenantId") Long tenantId, @Param("scheduledTime") Date scheduledTime);

    @Modifying
    @Query("DELETE FROM AgroTask t WHERE t.id = :id")
    void remove(@Param("id") Long id);

}

