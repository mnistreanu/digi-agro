package com.arobs.service.parcel;

import com.arobs.entity.parcel.Parcel;
import com.arobs.entity.parcel.ParcelGeometry;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.repository.ParcelGeometryRepository;
import com.arobs.service.BaseEntityService;
import com.arobs.utils.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ParcelGeometryService extends BaseEntityService<ParcelGeometry, ParcelGeometryRepository> {

    @Autowired
    private ParcelGeometryRepository parcelGeometryRepository;

    @Override
    public ParcelGeometryRepository getRepository() {
        return parcelGeometryRepository;
    }

    public ParcelGeometry find(Long parcelId) {
        return getRepository().findOne(parcelId);
    }

    @Transactional
    public void save(Parcel parcel, ParcelModel model) {

        ParcelGeometry entity;

        if (model.getId() == null) {
            entity = new ParcelGeometry();
            entity.setParcelId(parcel.getId());
        } else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);

        save(entity);
    }

    private void copyValues(ParcelGeometry entity, ParcelModel model) {
        List<BigDecimal[]> coordinates = model.getCoordinates();
        entity.setCoordinates(StaticUtil.gson.toJson(coordinates));
    }

}
