package com.arobs.repository;

import com.arobs.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {


    @Query("SELECT o FROM Owner o WHERE o.active = true")
    List<Owner> findAll();

    @Modifying
    @Query("UPDATE Owner o SET o.active = false WHERE o.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT COUNT(o) FROM Owner o WHERE o.name = :name AND o.active = true")
    long countByName(@Param("name") String name);

    @Query("SELECT COUNT(o) FROM Owner o WHERE o.name = :name AND o.id <> :id AND o.active = true")
    long countByNameEscapeId(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT o FROM Owner o WHERE o.name = :name AND o.active = true")
    Owner findByName(@Param("name") String name);
}
