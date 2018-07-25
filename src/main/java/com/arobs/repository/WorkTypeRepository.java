package com.arobs.repository;

import com.arobs.entity.WorkType;
import com.arobs.enums.WorkName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Deprecated
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {

    @Query("SELECT wt FROM WorkType wt WHERE wt.name IN (:names)")
    Collection<WorkType> findByNames(@Param("names") List<WorkName> workNames);
}
