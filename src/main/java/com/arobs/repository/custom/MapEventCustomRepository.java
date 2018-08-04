package com.arobs.repository.custom;

import com.arobs.model.UpdateFieldModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MapEventCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Modifying
    public void update(UpdateFieldModel model) {

        String queryStr = "UPDATE MapEvent e SET e." + model.getField() + " = ";


        if (model.getValue() == null) {
            queryStr += " null ";
        }
        else {
            queryStr += " '" + model.getValue() + "' ";
        }

        queryStr += " WHERE e.id = :id";

        em.createQuery(queryStr).setParameter("id", model.getId())
                .executeUpdate();
    }
}