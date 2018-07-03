package com.arobs.repository.custom;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Repository
public class TelemetryCustomRepositoryImpl implements TelemetryCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Modifying
    @Override
    public void updateCoordinate(Long id, String field, BigDecimal value) {

        String queryStr = "UPDATE Telemetry t SET t." + field + " = :value WHERE t.id = :id";

        em.createQuery(queryStr)
                .setParameter("value", value)
                .setParameter("id", id)
                .executeUpdate();
    }
}