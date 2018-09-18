package com.arobs.repository;

import com.arobs.entity.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesticideRepository extends JpaRepository<Pesticide, Long> {

    @Query("SELECT p FROM Pesticide p " +
            "ORDER BY p.nameRo ")
    List<Pesticide> find();

    @Query("SELECT p FROM Pesticide p " +
            "WHERE p.typeId= :typeId " +
            "ORDER BY p.nameRo ")
    List<Pesticide> find(@Param("typeId") Long typeId);
}

