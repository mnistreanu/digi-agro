package com.arobs.repository.custom;

import com.arobs.entity.Tenant;
import com.arobs.model.tenant.TenantFilterModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TenantCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Tenant> find(TenantFilterModel filterModel) {

        String queryStr = "SELECT t FROM Tenant t WHERE t.deletedAt IS NULL ";

        if (filterModel.getName() != null) {
            queryStr += " AND t.name LIKE '%" + filterModel.getName() + "%' ";
        }

        if (filterModel.getDescription() != null) {
            queryStr += " t.description LIKE '%" + filterModel.getDescription() + "%' ";
        }

        if (filterModel.getFiscalCode() != null) {
            queryStr += " t.fiscalCode LIKE '%" + filterModel.getFiscalCode() + "%' ";
        }

        if (filterModel.getCountry() != null) {
            queryStr += " t.country LIKE '%" + filterModel.getCountry() + "%' ";
        }

        if (filterModel.getCounty() != null) {
            queryStr += " t.county LIKE '%" + filterModel.getCounty() + "%' ";
        }

        if (filterModel.getAddress() != null) {
            queryStr += " t.address LIKE '%" + filterModel.getAddress() + "%' ";
        }

        if (filterModel.getPhones() != null) {
            queryStr += " t.phones LIKE '%" + filterModel.getPhones() + "%' ";

        }

        return em.createQuery(queryStr).getResultList();

    }

}