package com.arobs.repository.custom;

import com.arobs.model.tenantBranch.TenantBranchFilterRequestModel;
import com.arobs.model.tenantBranch.TenantBranchModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TenantBranchCustomRepositoryImpl implements TenantBranchCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TenantBranchModel> findByFilter(TenantBranchFilterRequestModel filterRequestModel) {

        String queryStr = "SELECT new com.arobs.model.tenantBranch.TenantBranchModel(b) " +
                " FROM TenantBranch b WHERE b.active = true ";

        if (filterRequestModel.getTenantId() != null) {
            queryStr += " AND b.tenant.id = " + filterRequestModel.getTenantId();
        }

        if (filterRequestModel.getName() != null) {
            queryStr += " AND b.name LIKE '%" + filterRequestModel.getName() + "%' ";
        }

        if (filterRequestModel.getDescription() != null) {
            queryStr += " b.description LIKE '%" + filterRequestModel.getDescription() + "%' ";
        }

        if (filterRequestModel.getCountry() != null) {
            queryStr += " b.country LIKE '%" + filterRequestModel.getCountry() + "%' ";
        }

        if (filterRequestModel.getCounty() != null) {
            queryStr += " b.county LIKE '%" + filterRequestModel.getCounty() + "%' ";
        }

        if (filterRequestModel.getVillageCity() != null) {
            queryStr += " b.villageCity LIKE '%" + filterRequestModel.getVillageCity() + "%' ";
        }

        if (filterRequestModel.getAddress() != null) {
            queryStr += " b.address LIKE '%" + filterRequestModel.getAddress() + "%' ";
        }

        if (filterRequestModel.getPhones() != null) {
            queryStr += " b.phones LIKE '%" + filterRequestModel.getPhones() + "%' ";

        }

        return em.createQuery(queryStr).getResultList();

    }
}