package com.arobs.repository.custom;

import com.arobs.entity.AgroWork;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class AgroWorkCustomRepository {

    @PersistenceContext
    private EntityManager em;


    public AgroWork findLast(Long parcelCropId) {

        String hql = "SELECT aw FROM AgroWork aw " +
                "WHERE aw.parcelCropId = :parcelCropId " +
                "ORDER BY aw.workDate DESC ";

        AgroWork entity = null;
        try {
            entity = (AgroWork) em.createQuery(hql)
                    .setParameter("parcelCropId", parcelCropId)
                    .setMaxResults(1)
                    .getSingleResult();
        }
        catch (NoResultException ignored) {}

        return entity;
    }


}