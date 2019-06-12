package com.arobs.service.parcel;

import com.arobs.entity.parcel.ParcelCropSeason;
import com.arobs.model.parcel.ParcelCropSeasonModel;
import com.arobs.repository.ParcelCropSeasonRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ParcelCropSeasonService extends BaseEntityService<ParcelCropSeason, ParcelCropSeasonRepository> {

    @Autowired
    private ParcelCropSeasonRepository parcelCropRepository;

    public ParcelCropSeasonRepository getRepository() {
        return parcelCropRepository;
    }

    public List<ParcelCropSeason> find(Long parcelId) {
        return getRepository().find(parcelId);
    }

    public ParcelCropSeason find(Long parcelId, int harvestYear) {
        return getRepository().find(parcelId, harvestYear);
    }

    public List<ParcelCropSeason> findByTenant(Long tenantId, int harvestYear) {
        return getRepository().findByTenant(tenantId, harvestYear);
    }

    public ParcelCropSeason findLast(Long parcelId) {
        int harvestYear = 0;
        List<ParcelCropSeason> list = this.find(parcelId);
        for (ParcelCropSeason pcs : list) {
            if (harvestYear < pcs.getCropSeason().getHarvestYear()) {
                harvestYear = pcs.getCropSeason().getHarvestYear();
            }
        }
        return getRepository().find(parcelId, harvestYear);
    }

    public ParcelCropSeasonModel getModel(ParcelCropSeason entity) {
        return new ParcelCropSeasonModel(entity);
    }

    @Transactional
    public ParcelCropSeason save(ParcelCropSeasonModel model) {

        ParcelCropSeason entity;

        if (model.getId() == null) {
            entity = new ParcelCropSeason();
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        entity = save(entity);

        return entity;
    }

    private void copyValues(ParcelCropSeason entity, ParcelCropSeasonModel model) {
        entity.setCropSeasonId(model.getCropSeasonId());
        entity.setCropVarietyId(model.getCropVarietyId());
        entity.setId(model.getId());
        entity.setParcelId(model.getParcelId());
        entity.setPlantedAt(model.getPlantedAt());
        entity.setPlantsOnRow(model.getPlantsOnRow());
        entity.setRowsOnParcel(model.getRowsOnParcel());
        entity.setSpaceBetweenPlants(model.getSpaceBetweenPlants());
        entity.setSpaceBetweenRows(model.getSpaceBetweenRows());
        entity.setYieldGoal(model.getYieldGoal());
    }
}
