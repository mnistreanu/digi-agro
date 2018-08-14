package com.arobs.repository;

import com.arobs.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {


    @Modifying
    @Query("UPDATE Tenant t " +
            "SET t.deletedAt = now(), t.deletedBy = :userId " +
            "WHERE t.id = :id")
    void remove(@Param("id") Long id, @Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM tenant_user WHERE tenant_id = :id", nativeQuery = true)
    void unSubscribeUsers(@Param("id") Long id);

    @Query("SELECT t FROM Tenant t WHERE t.deletedAt IS NULL")
    List<Tenant> findAll();

    @Query("SELECT t FROM UserAccount u " +
            "JOIN u.tenants t " +
            "WHERE u.id = :userId AND t.deletedAt IS NULL " +
            "GROUP BY t.id")
    List<Tenant> findByUser(@Param("userId") Long userId);
}
