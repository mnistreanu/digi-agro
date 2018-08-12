package com.arobs.repository;

import com.arobs.entity.TenantBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantBranchRepository extends JpaRepository<TenantBranch, Long> {

    @Modifying
    @Query("UPDATE TenantBranch b SET b.active = false WHERE b.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT b.id FROM TenantBranch b WHERE b.parent.id = :id")
    List<Long> getChildren(@Param("id") Long id);

    @Query("SELECT b FROM TenantBranch b WHERE b.active = true AND b.tenant.id IN (:tenants)")
    List<TenantBranch> find(@Param("tenants") List<Long> tenants);
}
