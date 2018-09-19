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
            "WHERE e.deletedAt IS null AND e.tenantId = :tenantId")
    List<Expense> find(@Param("tenantId") Long tenantId);

    @Modifying
    @Query("UPDATE Expense e SET e.deletedAt = :date WHERE e.id = :id")
    void remove(@Param("id") Long id, @Param("date") Date date);
}

