package com.arobs.repository;

import com.arobs.entity.ExpenseCategory;
import com.arobs.model.ListItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

    @Query("SELECT ec FROM ExpenseCategory ec WHERE ec.tenantId IS NULL")
    List<ExpenseCategory> findDefault();

//    @Query("SELECT ec FROM ExpenseCategory ec WHERE ec.tenantId IS NULL")
//    void copyDefault(Long tenantId);

    @Query("SELECT ec FROM ExpenseCategory ec WHERE ec.tenantId = :tenantId")
    List<ExpenseCategory> find(@Param("tenantId") Long tenantId);

}

