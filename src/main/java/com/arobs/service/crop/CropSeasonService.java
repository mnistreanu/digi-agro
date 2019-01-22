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
    private CropSeasonRepository cropSeasonRepository;


    public List<CropSeason> find(Long tenantId) {
        return getRepository().find(tenantId);
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
//        season.setCropId(model.getCropId());
//        season.setCropVarietyModel(model.getCropVarietyModel());
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
}
