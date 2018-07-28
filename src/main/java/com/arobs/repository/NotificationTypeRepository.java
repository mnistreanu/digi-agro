package com.arobs.repository;

import com.arobs.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer> {

    @Query("SELECT nt FROM NotificationType nt")
    List<NotificationType> find();
}

