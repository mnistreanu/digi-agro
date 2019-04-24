package com.arobs.service.parcel;

import com.arobs.entity.Parcel;
import com.arobs.entity.ParcelGeometry;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.repository.ParcelRepository;
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
        entity.setIrrigated(model.isIrrigated());
        entity.setDescription(model.getDescription());
    }

    public List<Parcel> findAll(List<Long> ids) {
        return getRepository().findAll(ids);
    }

    public ParcelModel getModel(Parcel parcel) {
        ParcelModel model = new ParcelModel(parcel);

        ParcelGeometry geometry = parcelGeometryService.find(parcel.getId());
        Type listType = new TypeToken<List<BigDecimal[]>>() {}.getType();
        List<BigDecimal[]> coordinates = StaticUtil.gson.fromJson(geometry.getCoordinates(), listType);
        model.setCoordinates(coordinates);

        return model;
    }
}
