package com.arobs.repository;

import com.arobs.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT e FROM Expense e " +
            "WHERE e.deletedAt IS null " +
            "AND e.tenantId = :tenantId ")
    List<Expense> find(@Param("tenantId") Long tenantId);

    @Query("SELECT e FROM Expense e " +
            "WHERE e.deletedAt IS null " +
            "AND e.tenantId = :tenantId " +
            "AND e.categoryId = (SELECT ec.id from ExpenseCategory ec WHERE ec.tenantId = :tenantId AND ec.parentId = :categoryId) ")
    List<Expense> find(@Param("tenantId") Long tenantId, @Param("categoryId") Long categoryId);

    @Query("SELECT e FROM Expense e " +
            "WHERE e.deletedAt IS null " +
            "AND e.tenantId = :tenantId " +
            "AND e.categoryId = (SELECT ec.id from ExpenseCategory ec WHERE ec.tenantId = :tenantId AND ec.parentId = :categoryId) " +
            "AND e.expenseDate BETWEEN :dateFrom AND :dateTo")
    List<Expense> find(@Param("tenantId") Long tenantId, @Param("categoryId") Long categoryId,
                       @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    @Modifying
    @Query("UPDATE Expense e SET e.deletedAt = :date WHERE e.id = :id")
    void remove(@Param("id") Long id, @Param("date") Date date);
}

