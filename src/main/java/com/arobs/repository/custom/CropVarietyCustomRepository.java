package com.arobs.repository.custom;


import com.arobs.entity.Crop;
import com.arobs.entity.CropVariety;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CropVarietyCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CropVariety> findByFilter(int page, int size, List<String> filters, List<String> sorts) {

        String querySelect = "Select c From CropVariety c where 1 = 1 ";

        String filt = "";
        String order = "";

        for (String filter: filters) {
            if (filter.contains("cropId")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.cropId = " + parts[1];
            }

            if (filter.contains("nameRo")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.nameRo LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("nameRu")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.nameRu LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("descriptionRo")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.descriptionRo LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("descriptionRu")) {
                String[] parts = filter.split(";");
                querySelect += " AND c.descriptionRu LIKE '%" + parts[1] + "%' ";
            }
        }

        for (String filter: sorts) {
            if (filter.contains("cropId")) {
                String[] parts = filter.split(";");
                querySelect += " ORDER BY c.cropId " + parts[1];
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

            if (filter.contains("descriptionRo")) {
                String[] parts = filter.split(";");
                querySelect += " ORDER BY  c.descriptionRo " + parts[1];
                break;
            }

            if (filter.contains("descriptionRu")) {
                String[] parts = filter.split(";");
                querySelect += " ORDER BY  c.descriptionRu " + parts[1];
                break;
            }
        }

        Query query = em.createQuery(querySelect + filt + order);
        query.setMaxResults(size);
        query.setFirstResult(page * size);

        return query.getResultList();
    }

    public long countFiltered(List<String> filters) {

        String queryCount = "Select COUNT(c.id) From CropVariety c where 1 = 1 ";

        for (String filter: filters) {
            if (filter.contains("cropId")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.cropId = " + parts[1];
            }

            if (filter.contains("nameRo")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.nameRo LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("nameRu")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.nameRu LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("descriptionRo")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.descriptionRo LIKE '%" + parts[1] + "%' ";
            }

            if (filter.contains("descriptionRu")) {
                String[] parts = filter.split(";");
                queryCount += " AND c.descriptionRu LIKE '%" + parts[1] + "%' ";
            }
        }

        return (long) em.createQuery(queryCount).getSingleResult();
    }
}
