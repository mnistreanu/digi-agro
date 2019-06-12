package com.arobs.repository;

import com.arobs.entity.notification.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {

    @Query("SELECT nt FROM NotificationType nt")
    List<NotificationType> find();
}

