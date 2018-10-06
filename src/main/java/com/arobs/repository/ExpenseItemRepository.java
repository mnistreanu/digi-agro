package com.arobs.repository;

import com.arobs.entity.ExpenseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseItemRepository extends JpaRepository<ExpenseItem, Long> {

    @Query("SELECT ei FROM ExpenseItem ei WHERE ei.expenseId = :expenseId")
    List<ExpenseItem> find(@Param("expenseId") Long expenseId);

    @Modifying
    @Query("DELETE FROM ExpenseItem ei WHERE ei.id IN (:ids)")
    void remove(@Param("ids") List<Long> ids);
}

