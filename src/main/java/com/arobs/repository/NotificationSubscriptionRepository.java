package com.arobs.repository;

import com.arobs.entity.notification.NotificationSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationSubscriptionRepository extends JpaRepository<NotificationSubscription, Long> {

    @Query("SELECT s FROM NotificationSubscription s WHERE s.userId = :userId")
    List<NotificationSubscription> find(@Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM NotificationSubscription s WHERE s.userId = :userId AND s.notificationType.id = :typeId")
    void delete(@Param("userId") Long userId, @Param("typeId") Long typeId);


}

