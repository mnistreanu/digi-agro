package com.arobs.repository.custom;

import com.arobs.entity.ParcelCropWork;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class ParcelCropWorkCustomRepository {

    @PersistenceContext
    private EntityManager em;


    public ParcelCropWork findLast(Long parcelCropId) {

        String hql = "SELECT pcw FROM ParcelCropWork pcw " +
                "WHERE pcw.parcelCropId = :parcelCropId " +
                "ORDER BY pcw.createdAt DESC ";

        ParcelCropWork entity = null;
        try {
            entity = (ParcelCropWork) em.createQuery(hql)
                    .setParameter("parcelCropId", parcelCropId)
                    .setMaxResults(1)
                    .getSingleResult();
        }
        catch (NoResultException ignored) {}

        return entity;
    }


}