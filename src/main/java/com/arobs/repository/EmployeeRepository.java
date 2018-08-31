package com.arobs.repository;

import com.arobs.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("UPDATE Employee e SET e.active = false WHERE e.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT e FROM Employee e WHERE e.tenant.id = :tenantId")
    List<Employee> find(@Param("tenantId") Long tenantId);

}
