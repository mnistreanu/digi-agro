package com.arobs.repository;

import com.arobs.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantBranchRepository extends JpaRepository<Branch, Long> {

    @Modifying
    @Query("DELETE FROM Branch b WHERE b.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT b.id FROM Branch b WHERE b.parent.id = :id")
    List<Long> getChildren(@Param("id") Long id);

    @Query("SELECT b FROM Branch b WHERE b.tenant.id IN (:tenants)")
    List<Branch> find(@Param("tenants") List<Long> tenants);
}
