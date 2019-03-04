package com.arobs.repository;

import com.arobs.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

    @Query("SELECT ec FROM ExpenseCategory ec " +
            "WHERE ec.tenantId = :tenantId AND ec.active = true")
    List<ExpenseCategory> find(@Param("tenantId") Long tenantId);

    @Query("SELECT ec FROM ExpenseCategory ec " +
            "WHERE ec.tenantId = :tenantId AND ec.parentId IS null AND ec.active = true")
    List<ExpenseCategory> findRoots(@Param("tenantId") Long tenantId);

    @Modifying
    @Query("UPDATE ExpenseCategory ec SET ec.active = false WHERE ec.id = :id")
    void remove(@Param("id") Long id);
}

