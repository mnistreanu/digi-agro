package com.arobs.repository.custom;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.MessageFormat;

@Repository
public class CommonCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean isUnique(String tableName, Long currentId, String field, String value) {

        String q = "SELECT COUNT(t) FROM {0} t WHERE t.{1} = ''{2}''";
        q = MessageFormat.format(q, tableName, field, value);

        if (currentId != null) {
            q += " AND t.id != " + currentId;
        }

        Long count = (Long) em.createQuery(q).getSingleResult();

        return count == 0;
    }

}