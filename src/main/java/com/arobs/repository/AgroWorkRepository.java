package com.arobs.repository;

import com.arobs.entity.AgroWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgroWorkRepository extends JpaRepository<AgroWork, Long> {


    @Query("SELECT aw FROM AgroWork aw " +
            "WHERE aw.parcelCropId = :parcelCropId " +
            "ORDER BY aw.workDate ")
    List<AgroWork> find(@Param("parcelCropId") Long parcelCropId);
}

