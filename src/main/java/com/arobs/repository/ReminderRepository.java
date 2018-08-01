package com.arobs.repository;

import com.arobs.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    @Query("SELECT r FROM Reminder r " +
            "WHERE r.tenantId = :tenantId " +
            "ORDER BY r.starting ")
    List<Reminder> find(@Param("tenantId") Long tenantId);

    @Query("SELECT r FROM Reminder r " +
            "WHERE r.tenantId = :tenantId " +
            "AND r.starting >= :starting " +
            "ORDER BY r.starting ")
    List<Reminder> find(@Param("tenantId") Long tenantId, @Param("starting") Date starting);


    @Modifying
    @Query("DELETE FROM Reminder r WHERE r.id = :id")
    void remove(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Reminder r SET r.starting = :starting, r.ending = :ending WHERE r.id = :id")
    void changeSchedule(@Param("id") Long id, @Param("starting") Date start, @Param("ending") Date ending);
}

