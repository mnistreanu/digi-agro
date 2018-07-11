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

    @Query("SELECT COUNT(t) FROM Tenant t WHERE t.name = :name AND t.active = true")
    long countByName(@Param("name") String name);

    @Query("SELECT COUNT(t) FROM Tenant t WHERE t.name = :name AND t.id <> :id AND t.active = true")
    long countByNameEscapeId(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT COUNT(t) FROM Tenant t WHERE t.fiscalCode = :fiscalCode AND t.active = true")
    long countByFiscalCode(@Param("fiscalCode") String fiscalCode);

    @Query("SELECT COUNT(t) FROM Tenant t WHERE t.fiscalCode = :fiscalCode AND t.id <> :id AND t.active = true")
    long countByFiscalCodeEscapeId(@Param("id") Long id, @Param("fiscalCode") String fiscalCode);

    @Modifying
    @Query("UPDATE Tenant t SET t.active = false WHERE t.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT new com.arobs.model.ListItemModel(t.id, t.name) FROM Tenant t WHERE t.active = true")
    List<ListItemModel> fetchAllListItems();

    @Query("SELECT new com.arobs.model.ListItemModel(t.id, t.name) FROM UserAccount u JOIN u.tenants t " +
            " WHERE u.id = :userId AND t.active = true GROUP BY t.id")
    List<ListItemModel> fetchListItemsByUser(@Param("userId") Long userId);

    @Query("SELECT t FROM Tenant t WHERE t.id IN (:ids)")
    List<Tenant> findByIds(@Param("ids") List<Long> ids);
}
