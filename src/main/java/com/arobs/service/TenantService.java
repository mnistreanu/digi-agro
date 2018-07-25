package com.arobs.service;

import com.arobs.entity.Tenant;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.ListItemModel;
import com.arobs.model.tenant.TenantFilterModel;
import com.arobs.model.tenant.TenantModel;
import com.arobs.repository.TenantRepository;
import com.arobs.repository.custom.TenantCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService implements HasRepository<TenantRepository> {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private TenantCustomRepository tenantCustomRepository;
    @Autowired
    private AuthService authService;

    @Override
    public TenantRepository getRepository() {
        return tenantRepository;
    }

    public Tenant findOne(Long id) {
        return getRepository().findOne(id);
    }
//
//    public List<ListItemModel> fetchListItems() {
//        if (authService.isSuperAdminOrAdmin()) {
//            return getRepository().fetchAllListItems();
//        }
//        else {
//            return getRepository().fetchListItemsByUser(authService.getCurrentUser().getId());
//        }
//    }

    public List<TenantModel> findByFilter(TenantFilterModel filterRequestModel) {
        List<TenantModel> list = new ArrayList<TenantModel>();
        List<Tenant> tenants = tenantCustomRepository.find(filterRequestModel);
        for (Tenant t:tenants) {
            list.add(new TenantModel(t));
        }
        return list;
    }

//    public boolean validateName(Long id, String name) {
//        if (id == -1) {
//            return getRepository().countByName(name) == 0;
//        }
//        return getRepository().countByNameEscapeId(id, name) == 0;
//    }


    public boolean validateFiscalCode(Long id, String code) {
        long count = getRepository().countByFiscalCode(code);
        return count == 0;
    }

    @Transactional
    public Tenant save(TenantModel model) {

        Tenant tenant;

        if (model.getId() == null) {
            tenant = new Tenant();
        }
        else {
            tenant = findOne(model.getId());
        }

        copyValues(tenant, model);

        return save(tenant);
    }

    private void copyValues(Tenant entity, TenantModel model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setFiscalCode(model.getFiscalCode());
        entity.setCountry(model.getCountry());
        entity.setCounty(model.getCounty());
//        entity.setVillageCity(model.getVillageCity());
        entity.setAddress(model.getAddress());
        entity.setPhones(model.getPhones());
    }

    @Transactional
    public Tenant save(Tenant tenant) {
        return getRepository().save(tenant);
    }

    @Transactional
    public void remove(Long id, Long userId) {
        getRepository().remove(id, userId);
    }

    public List<Tenant> findByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        return getRepository().find(ids);
    }
}
