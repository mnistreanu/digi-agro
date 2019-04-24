package com.arobs.service.parcel;

import com.arobs.entity.ParcelEvent;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.parcel.ParcelEventModel;
import com.arobs.repository.ParcelEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ParcelEventService implements HasRepository<ParcelEventRepository> {

    @Autowired
    private ParcelEventRepository parcelEventRepository;

    @Override
    public ParcelEventRepository getRepository() {
        return parcelEventRepository;
    }

    public List<ParcelEvent> find(Long parcelId) {
        return getRepository().find(parcelId);
    }
    
    public ParcelEvent findOne(Long id) {
        return getRepository().findOne(id);
    }    


    @Transactional
    public void remove(Long id) {
        getRepository().delete(id);
    }

    @Transactional
    public ParcelEvent save(ParcelEventModel model) {

        ParcelEvent entity;

        if (model.getId() == null) {
            entity = new ParcelEvent();
            entity.setParcelId(model.getParcelId());
        }
        else {
            entity = findOne(model.getId());
        }

        copyValues(entity, model);
        entity = getRepository().save(entity);

        return entity;
    }

    private void copyValues(ParcelEvent entity, ParcelEventModel model) {
        entity.setDate(model.getDate());
        entity.setTitle(model.getTitle());
        entity.setDescription(model.getDescription());

        entity.setEventTypeId(model.getEventTypeId());
        entity.setEventTypeDetailId(model.getEventTypeDetailId());
    }

}
