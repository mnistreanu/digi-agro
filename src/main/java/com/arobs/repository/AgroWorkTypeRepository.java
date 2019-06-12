package com.arobs.repository;

import com.arobs.entity.agro.AgroWorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgroWorkTypeRepository extends JpaRepository<AgroWorkType, Long> {

    @Query("SELECT t FROM AgroWorkType t " +
            "WHERE t.tenantId IS NULL " +
            "ORDER BY t.nameRo ")
    List<AgroWorkType> find();

    @Query("SELECT t FROM AgroWorkType t " +
            "WHERE t.tenantId = :tenantId " +
            "OR t.tenantId IS NULL " +
            "ORDER BY t.nameRo ")
    List<AgroWorkType> find(@Param("tenantId") Long tenantId);
}

