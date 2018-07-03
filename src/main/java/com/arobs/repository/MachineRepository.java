package com.arobs.repository;

import com.arobs.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    @Query("SELECT m FROM Machine m WHERE m.active = true")
    List<Machine> findAll();

    @Query("SELECT m.identifier FROM Machine m WHERE m.active = true")
    List<String> fetchIdentifiers();

    @Modifying
    @Query("UPDATE Machine m SET m.active = false WHERE m.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT COUNT(m) FROM Machine m WHERE m.identifier = :identifier AND m.active = true")
    long countByIdentifier(@Param("identifier") String identifier);

    @Query("SELECT COUNT(m) FROM Machine m WHERE m.identifier = :identifier AND m.id <> :id AND m.active = true")
    long countByIdentifierEscapeId(@Param("id") Long id, @Param("identifier") String identifier);

    @Query("SELECT m FROM Machine m WHERE m.identifier = :identifier AND m.active = true")
    Machine findByIdentifier(@Param("identifier") String identifier);
}
