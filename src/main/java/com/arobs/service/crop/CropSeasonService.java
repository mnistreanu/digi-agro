package com.arobs.service.crop;

import com.arobs.entity.CropSeason;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.crop.CropSeasonModel;
import com.arobs.repository.CropSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CropSeasonService implements HasRepository<CropSeasonRepository> {

    @Autowired
    private CropService cropService;

    @Autowired
    private CropVarietyService cropVarietyService;

    @Autowired
    private CropSeasonRepository cropSeasonRepository;


    public List<CropSeason> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<CropSeason> find(Long tenantId, Integer harvestYear) {
        return getRepository().find(tenantId, harvestYear);
    }

    public CropSeason findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void delete(Long id) {
        getRepository().delete(id);
    }

    @Transactional
    public CropSeason save(CropSeason cropSeason) {
        return getRepository().save(cropSeason);
    }

    @Transactional
    public CropSeason save(CropSeasonModel model) {

        CropSeason season;

        if (model.getId() == null) {
            season = new CropSeason();
        }
        else {
            season = getRepository().findOne(model.getId());
        }

        season.setTenantId(model.getTenantId());
        season.setCrop(cropService.findOne(model.getCropId()));
        if (model.getCropVarietyId() != null) {
            season.setCropVariety(cropVarietyService.findOne(model.getCropVarietyId()));
        }
        season.setHarvestYear(model.getHarvestYear());
        season.setStartDate(model.getStartDate());
        season.setEndDate(model.getEndDate());
        season.setYieldGoal(model.getYieldGoal());

        return save(season);
    }

    @Override
    public CropSeasonRepository getRepository() {
        return cropSeasonRepository;
    }

    public List<Integer> getYears(Long tenantId) {
        return getRepository().getYears(tenantId);
    }
}
