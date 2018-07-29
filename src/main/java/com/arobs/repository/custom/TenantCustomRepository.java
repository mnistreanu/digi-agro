package com.arobs.repository.custom;

import com.arobs.entity.Tenant;
import com.arobs.model.tenant.TenantFilterModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.MessageFormat;
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

//        if (filterModel.getVillageCity() != null) {
//            queryStr += " t.villageCity LIKE '%" + filterModel.getVillageCity() + "%' ";
//        }

        if (filterModel.getAddress() != null) {
            queryStr += " t.address LIKE '%" + filterModel.getAddress() + "%' ";
        }

        if (filterModel.getPhones() != null) {
            queryStr += " t.phones LIKE '%" + filterModel.getPhones() + "%' ";

        }

        return em.createQuery(queryStr).getResultList();

    }

    public boolean isUnique(Long currentId, String field, String value) {

        String q = "SELECT COUNT(t) " +
                " FROM Tenant t WHERE t.{0} = {1}";
        q = MessageFormat.format(q, field, value);

        if (currentId != null) {
            q += " AND t.id != " + currentId;
        }

        Long count = (Long) em.createQuery(q).getSingleResult();

        return count == 0;
    }

//
//    @Query("SELECT new com.arobs.model.ListItemModel(t.id, t.name) FROM Tenant t WHERE t.deletedAt IS NULL")
//    List<ListItemModel> fetchAllListItems();
//
//    @Query("SELECT new com.arobs.model.ListItemModel(t.id, t.name) " +
//            "FROM UserAccount u " +
//            "JOIN u.tenants t " +
//            "WHERE u.id = :userId AND t.deletedAt IS NULL " +
//            "GROUP BY t.id")
//    List<ListItemModel> fetchListItemsByUser(@Param("userId") Long userId);

}