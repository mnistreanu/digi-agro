package com.arobs.repository;

import com.arobs.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT cv FROM City cv " +
            "WHERE upper(cv.countyId) = upper(:countyId) AND upper(cv.countryId) = upper(:countryId) " +
            "ORDER BY cv.nameRo ")
    List<City> find(@Param("countryId") String countryId,
                    @Param("countyId") String countyId);

    @Query("SELECT cv FROM City cv " +
            "WHERE upper(cv.countyId) = upper(:countyId) AND upper(cv.countryId) = upper(:countryId) " +
            "AND (upper(cv.nameRo) LIKE upper(:name) OR upper(cv.nameRu) LIKE upper(:name)) " +
            "ORDER BY cv.nameRo")
    List<City> find(@Param("countryId") String countryId,
                    @Param("countyId") String countyId,
                    @Param("name") String name);

}

