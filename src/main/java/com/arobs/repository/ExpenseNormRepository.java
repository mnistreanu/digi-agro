package com.arobs.repository;

import com.arobs.entity.ExpenseNorm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseNormRepository extends JpaRepository<ExpenseNorm, Long> {

    @Query("SELECT ed FROM ExpenseNorm ed WHERE ed.expenseItemId = :expenseItemId")
    ExpenseNorm find(@Param("expenseItemId") Long expenseItemId);

}

