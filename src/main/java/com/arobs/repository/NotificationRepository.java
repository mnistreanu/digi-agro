package com.arobs.repository;

import com.arobs.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n " +
            "WHERE n.userId = :userId " +
            "ORDER BY n.dateTo ")
    List<Notification> findAll(@Param("userId") Long userId);

    @Query("SELECT n FROM Notification n " +
            "WHERE n.userId = :userId " +
            "AND n.dateTo > :dateFrom " +
            "ORDER BY n.dateTo ")
    List<Notification> findAll(@Param("userId") Long userId, @Param("dateFrom") Date dateFrom);

    @Query("SELECT n FROM Notification n " +
            "WHERE n.userId = :userId " +
            "AND n.dateTo > :dateFrom " +
            "AND n.seenAt IS NULL " +
            "ORDER BY n.dateTo ")
    List<Notification> findNotSeen(@Param("userId") Long userId, @Param("dateFrom") Date dateFrom );

    @Modifying
    @Query("UPDATE Notification n SET n.seenAt = :date " +
            "WHERE n.id IN (:ids) AND n.seenAt IS NULL")
    void see(@Param("ids") List<Long> ids, @Param("date") Date date);
}

