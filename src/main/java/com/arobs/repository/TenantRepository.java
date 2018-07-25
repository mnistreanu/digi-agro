package com.arobs.repository;

import com.arobs.entity.Tenant;
import com.arobs.model.ListItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {


    @Query("SELECT COUNT(t) FROM Tenant t WHERE t.fiscalCode = :fiscalCode AND t.deletedAt IS NULL ")
    long countByFiscalCode(@Param("fiscalCode") String fiscalCode);

    @Modifying
    @Query("UPDATE Tenant t " +
            "SET t.deletedAt = now(), t.deletedBy = :userId " +
            "WHERE t.id = :id")
    void remove(@Param("id") Long id, @Param("userId") Long userId);
//
//    @Query("SELECT new com.arobs.model.ListItemModel(t.id, t.name) FROM Tenant t WHERE t.deletedAt IS NULL")
//    List<ListItemModel> fetchAllListItems();
//
//    @Query("SELECT new com.arobs.model.ListItemModel(t.id, t.name) " +
//            "FROM UserAccount u " +
//            "JOIN u.tenants t " +
//            "WHERE u.id = :userId AND t.deletedAt IS NULL " +
//            "GROUP BY t.id")
//    List<ListItemModel> fetchListItemsByUser(@Param("userId") Long userId);

    @Query("SELECT t FROM Tenant t WHERE t.id IN (:ids)")
    List<Tenant> find(@Param("ids") List<Long> ids);
}
