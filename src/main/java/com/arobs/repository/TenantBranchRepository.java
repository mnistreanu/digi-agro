package com.arobs.repository;

import com.arobs.entity.TenantBranch;
import com.arobs.model.ListItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantBranchRepository extends JpaRepository<TenantBranch, Long> {

    @Query("SELECT COUNT(b) FROM TenantBranch b WHERE b.name = :name AND b.active = true")
    long countByName(@Param("name") String name);

    @Query("SELECT COUNT(b) FROM TenantBranch b WHERE b.name = :name AND b.id <> :id AND b.active = true")
    long countByNameEscapeId(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query("UPDATE TenantBranch b SET b.active = false WHERE b.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT new com.arobs.model.ListItemModel(b.id, b.name) " +
            " FROM TenantBranch b WHERE b.active = true AND b.tenant.id = :tenantId")
    List<ListItemModel> fetchItemsByTenant(@Param("tenantId") Long tenantId);

    @Query("SELECT new com.arobs.model.ListItemModel(b.id, b.name) " +
            " FROM TenantBranch b WHERE b.active = true AND b.tenant.id = :tenantId AND b.id NOT IN (:skipItems)")
    List<ListItemModel> fetchItemsByTenantAndNotIn(@Param("tenantId") Long tenantId, @Param("skipItems") List<Long> skipItems);

    @Query("SELECT b.id FROM TenantBranch b WHERE b.parent.id = :id")
    List<Long> getChildren(@Param("id") Long id);

}
