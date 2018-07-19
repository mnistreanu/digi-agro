package com.arobs.repository.custom;

import com.arobs.model.tenant.TenantFilterRequestModel;
import com.arobs.model.tenant.TenantModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TenantCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<TenantModel> findByFilter(TenantFilterRequestModel filterRequestModel) {

        String queryStr = "SELECT new com.arobs.model.tenant.TenantModel(t) " +
                " FROM Tenant t WHERE t.active = true ";

        if (filterRequestModel.getName() != null) {
            queryStr += " AND t.name LIKE '%" + filterRequestModel.getName() + "%' ";
        }

        if (filterRequestModel.getDescription() != null) {
            queryStr += " t.description LIKE '%" + filterRequestModel.getDescription() + "%' ";
        }

        if (filterRequestModel.getFiscalCode() != null) {
            queryStr += " t.fiscalCode LIKE '%" + filterRequestModel.getFiscalCode() + "%' ";
        }

        if (filterRequestModel.getCountry() != null) {
            queryStr += " t.country LIKE '%" + filterRequestModel.getCountry() + "%' ";
        }

        if (filterRequestModel.getCounty() != null) {
            queryStr += " t.county LIKE '%" + filterRequestModel.getCounty() + "%' ";
        }

        if (filterRequestModel.getVillageCity() != null) {
            queryStr += " t.villageCity LIKE '%" + filterRequestModel.getVillageCity() + "%' ";
        }

        if (filterRequestModel.getAddress() != null) {
            queryStr += " t.address LIKE '%" + filterRequestModel.getAddress() + "%' ";
        }

        if (filterRequestModel.getPhones() != null) {
            queryStr += " t.phones LIKE '%" + filterRequestModel.getPhones() + "%' ";

        }

        return em.createQuery(queryStr).getResultList();

    }
}