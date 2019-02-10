package com.arobs.service.parcel;

import com.arobs.entity.*;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.repository.ParcelRepository;
import com.arobs.service.agrowork.AgroWorkService;
import com.arobs.service.agrowork.AgroWorkTypeService;
import com.arobs.service.crop.CropService;
import com.arobs.utils.StaticUtil;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ParcelService implements HasRepository<ParcelRepository> {

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private ParcelGeometryService parcelGeometryService;

    @Autowired
    private ParcelCropSeasonService parcelCropService;
    @Autowired
    private CropService cropService;
    @Autowired
    private AgroWorkService agroWorkService;
    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    public ParcelRepository getRepository() {
        return parcelRepository;
    }

    public List<Parcel> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public Parcel findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional
    public Parcel save(ParcelModel model, Long tenant) {

        Parcel entity;

        if (model.getId() == null) {
            entity = new Parcel();
            entity.setTenantId(tenant);
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        entity = getRepository().save(entity);

        parcelGeometryService.save(entity, model);

        return entity;
    }

    private void copyValues(Parcel entity, ParcelModel model) {
        entity.setName(model.getName());
        entity.setCadasterNumber(model.getCadasterNumber());
        entity.setLandWorthinessPoints(model.getLandWorthinessPoints());
        entity.setArea(model.getArea());
        entity.setDescription(model.getDescription());
    }

    public List<Parcel> findAll(List<Long> ids) {
        return getRepository().findAll(ids);
    }

    public ParcelModel getModel(Parcel parcel) {
        ParcelModel model = new ParcelModel(parcel);

//        ParcelCrop parcelCrop = parcelCropService.find(parcel.getId());
//        if (parcelCrop != null) {
//            Crop crop = cropService.findOne(parcelCrop.getCropId());
//            model.setupCropSeason(parcelCrop, crop);
//
//            AgroWork lastAgroWork = agroWorkService.findLast(parcelCrop.getId());
//            if (lastAgroWork != null) {
//                AgroWorkType agroWorkType = agroWorkTypeService.findOne(lastAgroWork.getWorkType().getId());
//                model.setupLastCropWork(lastAgroWork, agroWorkType);
//            }
//        }

        ParcelGeometry geometry = parcelGeometryService.find(parcel.getId());
        Type listType = new TypeToken<List<BigDecimal[]>>() {}.getType();
        List<BigDecimal[]> coordinates = StaticUtil.gson.fromJson(geometry.getCoordinates(), listType);
        model.setCoordinates(coordinates);

        return model;
    }
}
