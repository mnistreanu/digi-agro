package com.arobs.service.crop;

import com.arobs.entity.crop.CropSeason;
import com.arobs.model.crop.CropSeasonModel;
import com.arobs.repository.CropSeasonRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CropSeasonService extends BaseEntityService<CropSeason, CropSeasonRepository> {

    @Autowired
    private CropService cropService;
    @Autowired
    private CropVarietyService cropVarietyService;
    @Autowired
    private CropSeasonRepository cropSeasonRepository;

    @Override
    public CropSeasonRepository getRepository() {
        return cropSeasonRepository;
    }

    public List<CropSeason> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public List<CropSeason> find(Long tenantId, Integer harvestYear) {
        return getRepository().find(tenantId, harvestYear);
    }

    @Transactional
    public CropSeason save(CropSeasonModel model) {

        CropSeason season;

        if (model.getId() == null) {
            season = new CropSeason();
        } else {
            season = getRepository().findOne(model.getId());
        }

        season.setTenantId(model.getTenantId());
        season.setCrop(cropService.getOne(model.getCropId()));
        if (model.getCropVarietyId() != null) {
            season.setCropVariety(cropVarietyService.getOne(model.getCropVarietyId()));
        }
        season.setHarvestYear(model.getHarvestYear());
        season.setStartDate(model.getStartDate());
        season.setEndDate(model.getEndDate());
        season.setYieldGoal(model.getYieldGoal());

        return save(season);
    }

    public List<Integer> getYears(Long tenantId) {
        return getRepository().getYears(tenantId);
    }
}
