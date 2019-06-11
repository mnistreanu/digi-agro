package com.arobs.repository;

import com.arobs.entity.expense.Expense;
import com.arobs.model.expense.ExpenseSeasonGroupModel;
import com.arobs.model.expense.ExpenseSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    @Query("SELECT e FROM Expense e " +
            "WHERE e.tenant = :tenantId AND e.cropSeasonId = :cropSeasonId " +
            "ORDER BY e.date, e.category.id")
    List<Expense> find(@Param("tenantId") Long tenantId,
                       @Param("cropSeasonId") Long cropSeasonId);

    @Query("SELECT new com.arobs.model.expense.ExpenseSeasonGroupModel( " +
            "e.cropSeasonId, " +
            "e.category.id, " +
            "EXTRACT(MONTH FROM e.date), " +
            "SUM(e.cost) " +
            ") " +
            "FROM Expense e " +
            "WHERE e.tenant = :tenantId " +
            "GROUP BY e.cropSeasonId, e.category.id, EXTRACT(MONTH FROM e.date)")
    List<ExpenseSeasonGroupModel> getExpenseSeasonGroupModels(@Param("tenantId") Long tenantId);

    @Query("SELECT new com.arobs.model.expense.ExpenseSummaryModel( " +
            "ec.name, " +
            "SUM(e.cost) " +
            ") " +
            "FROM Expense e " +
            "JOIN e.category ec " +
            "WHERE e.cropSeason.id = :cropSeasonId " +
            "GROUP BY ec.name")
    List<ExpenseSummaryModel> getSummaryModels(@Param("cropSeasonId") Long cropSeasonId);
}

