package com.arobs.repository.custom;

import com.arobs.entity.forecast.ForecastSnapshot;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class ForecastSnapshotCustomRepository {

    @PersistenceContext
    private EntityManager em;


    public ForecastSnapshot getLastSnapshot(Long forecastId) {

        String hql = "SELECT fs FROM ForecastSnapshot fs " +
                "WHERE fs.forecastId = :forecastId " +
                "ORDER BY fs.createdAt DESC ";

        ForecastSnapshot snapshot = null;
        try {
            snapshot = (ForecastSnapshot) em.createQuery(hql)
                    .setParameter("forecastId", forecastId)
                    .setMaxResults(1)
                    .getSingleResult();
        }
        catch (NoResultException ignored) {}

        return snapshot;
    }


}