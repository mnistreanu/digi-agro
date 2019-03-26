package com.arobs.service.parcel;

import com.arobs.entity.CropSeason;
import com.arobs.entity.Parcel;
import com.arobs.entity.ParcelCropSeason;
import com.arobs.entity.ParcelGeometry;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.parcel.ParcelCropSeasonModel;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.repository.ParcelCropSeasonRepository;
import com.arobs.utils.StaticUtil;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ParcelCropSeasonService implements HasRepository<ParcelCropSeasonRepository> {

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

    public ParcelCropSeason findLast(Long parcelId) {
        int harvestYear = 0;
        List<ParcelCropSeason> list = this.find(parcelId);
        for (ParcelCropSeason pcs:list) {
            if (harvestYear < pcs.getCropSeason().getHarvestYear()) {
                harvestYear = pcs.getCropSeason().getHarvestYear();
            }
        }
        return getRepository().find(parcelId, harvestYear);
    }

    public ParcelCropSeason findOne(Long id) {
        return getRepository().findOne(id);
    }

    public ParcelCropSeasonModel getModel(ParcelCropSeason entity) {
        ParcelCropSeasonModel model = new ParcelCropSeasonModel(entity);
        return model;
    }

    @Transactional
    public ParcelCropSeason save(ParcelCropSeasonModel model) {

        ParcelCropSeason entity;

        if (model.getId() == null) {
            entity = new ParcelCropSeason();
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        entity = getRepository().save(entity);

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
