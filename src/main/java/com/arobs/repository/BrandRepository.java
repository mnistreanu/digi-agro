package com.arobs.repository;

import com.arobs.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
    @Query("SELECT b FROM Brand b")
    List<Brand> findAll();

    @Modifying
    @Query("DELETE FROM Brand b WHERE b.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT b FROM Brand b WHERE b.name = :name")
    Brand find(@Param("name") String name);
}
