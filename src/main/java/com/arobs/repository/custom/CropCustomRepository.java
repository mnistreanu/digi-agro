package com.arobs.repository.custom;


import com.arobs.entity.Crop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CropCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Crop> findByFilter(int page, int size, List<String> filters, List<String> sorts) {

        String querySelect = "Select c From Crop c where 1 = 1 ";

        String filt = "";
        String order = "";

        for (String filter: filters) {
            if (filter.contains("cropCategoryId")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.cropCategoryId = " + parts[1];
            }

            if (filter.contains("nameRo")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.nameRo LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("nameRu")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.nameRu LIKE '%" + parts[1] + "%' ";
            }
        }

        for (String filter: sorts) {
            if (filter.contains("cropCategoryId")) {
                String[] parts = filter.split(";");
                querySelect += " ORDER BY c.cropCategoryId " + parts[1];
                break;
            }

            if (filter.contains("nameRo")) {
                String[] parts = filter.split(";");
                querySelect += " ORDER BY  c.nameRo " + parts[1];
                break;
            }

            if (filter.contains("nameRu")) {
                String[] parts = filter.split(";");
                querySelect += " ORDER BY  c.nameRu " + parts[1];
                break;
            }
        }

        Query query = em.createQuery(querySelect + filt + order);
        query.setMaxResults(size);
        query.setFirstResult(page * size);

        return query.getResultList();
    }

    public long countFiltered(List<String> filters) {

        String queryCount = "Select COUNT(c.id) From Crop c where 1 = 1 ";

        for (String filter: filters) {
            if (filter.contains("cropCategoryId")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.cropCategoryId = " + parts[1];
            }

            if (filter.contains("nameRo")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.nameRo LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("nameRu")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.nameRu LIKE '%" + parts[1] + "%' ";
            }
        }

        return (long) em.createQuery(queryCount).getSingleResult();
    }
}
