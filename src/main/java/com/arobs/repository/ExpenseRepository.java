package com.arobs.repository;

import com.arobs.entity.Expense;
import com.arobs.model.expense.ExpenseSeasonGroupModel;
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
            "ORDER BY e.date, e.categoryId")
    List<Expense> find(@Param("tenantId") Long tenantId,
                       @Param("cropSeasonId") Long cropSeasonId);

    @Modifying
    @Query("DELETE FROM Expense e WHERE e.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT new com.arobs.model.expense.ExpenseSeasonGroupModel( " +
            " e.cropSeasonId, " +
            " e.categoryId, " +
            " MIN(ec.name), " +
            " EXTRACT(MONTH FROM e.date), " +
            " SUM(e.cost) " +
            " ) " +
            "FROM Expense e " +
            "LEFT JOIN e.category ec " +
            "WHERE e.tenant = :tenantId " +
            "GROUP BY e.cropSeasonId, e.categoryId, EXTRACT(MONTH FROM e.date)")
    List<ExpenseSeasonGroupModel> getExpenseSeasonGroupModels(@Param("tenantId") Long tenantId);
}

