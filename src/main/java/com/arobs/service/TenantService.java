package com.arobs.service;

import com.arobs.entity.Tenant;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.tenant.TenantFilterModel;
import com.arobs.model.tenant.TenantModel;
import com.arobs.repository.TenantRepository;
import com.arobs.repository.custom.CommonCustomRepository;
import com.arobs.repository.custom.TenantCustomRepository;
import com.arobs.utils.StaticUtil;
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
    private CommonCustomRepository commonCustomRepository;
    @Autowired
    private AuthService authService;

    @Override
    public TenantRepository getRepository() {
        return tenantRepository;
    }

    public Tenant findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<Tenant> findByUser() {
        if (authService.isSuperAdmin()) {
            return getRepository().findAll();
        }
        else {
            return getRepository().findByUser(authService.getCurrentUser().getId());
        }
    }

    public List<Tenant> find(TenantFilterModel filterModel) {
        return tenantCustomRepository.find(filterModel);
    }

    public boolean isUnique(Long currentId, String field, String value) {
        if (!(field.equals("name") || field.equals("fiscalCode"))) {
            throw new IllegalArgumentException("Invalid field name");
        }

        return commonCustomRepository.isUnique("Tenant", currentId, field, value);
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
        entity.setCityId(model.getCityId());
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
        getRepository().unSubscribeUsers(id);
    }

    public List<Tenant> findAll(List<Long> ids) {
        if (StaticUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return getRepository().findAll(ids);
    }
}
