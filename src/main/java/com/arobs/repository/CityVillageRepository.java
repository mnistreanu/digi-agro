package com.arobs.repository;

import com.arobs.entity.CityVillage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityVillageRepository extends JpaRepository<CityVillage, Long> {

    @Query("SELECT cv FROM CityVillage cv " +
            "WHERE cv.countyId = :countyId")
    List<CityVillage> findByCountryIdAndCountyId(@Param("countryId") String countryId,
                                                 @Param("countyId") String countyId);

    @Query("SELECT cv FROM CityVillage cv " +
            "WHERE cv.countyId = :countyId " +
            "AND (cv.nameRo LIKE :name OR cv.nameRu LIKE :name)")
    List<CityVillage> findByNameAndCountryIdAndCountyId(@Param("countryId") String countryId,
                                                        @Param("countyId") String countyId,
                                                        @Param("name") String name);

}

