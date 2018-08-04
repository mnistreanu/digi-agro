package com.arobs.repository.custom;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Repository
public class MachineTelemetryCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Modifying
    public void updateCoordinate(Long id, String field, BigDecimal value) {

        String queryStr = "UPDATE MachineTelemetry t SET t." + field + " = :value WHERE t.id = :id";

        em.createQuery(queryStr)
                .setParameter("value", value)
                .setParameter("id", id)
                .executeUpdate();
    }
}