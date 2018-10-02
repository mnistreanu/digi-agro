package com.arobs.service.parcel;

import com.arobs.entity.Parcel;
import com.arobs.entity.ParcelGeometry;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.ParcelGeometryRepository;
import com.arobs.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService implements HasRepository<ParcelRepository> {

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private ParcelGeometryRepository parcelGeometryRepository;

    public ParcelRepository getRepository() {
        return parcelRepository;
    }

    public List<Parcel> find(Long tenantId) {
        return getRepository().find(tenantId);
    }

    public ParcelGeometry findParcelGeometry(Long parcelId) {
        return parcelGeometryRepository.findOne(parcelId);
    }

    public List<Parcel> findAll(List<Long> ids) {
        return getRepository().findAll(ids);
    }

}
