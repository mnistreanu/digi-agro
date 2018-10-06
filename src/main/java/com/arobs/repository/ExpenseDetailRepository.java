package com.arobs.repository;

import com.arobs.entity.ExpenseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseDetailRepository extends JpaRepository<ExpenseDetail, Long> {

    @Query("SELECT ed FROM ExpenseDetail ed WHERE ed.expenseItemId = :expenseItemId")
    ExpenseDetail find(@Param("expenseItemId") Long expenseItemId);
}

