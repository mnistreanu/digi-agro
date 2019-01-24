package com.arobs.service.crop;

import com.arobs.entity.SubCrop;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.crop.SubCropModel;
import com.arobs.repository.SubCropRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCropService implements HasRepository<SubCropRepository> {

    @Autowired
    private SubCropRepository subCropRepository;

    @Autowired
    private CropService cropService;

    public List<SubCrop> find(Long cropId) {
        return getRepository().find(cropId);
    }

    public SubCrop findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void delete(Long id) {
        getRepository().delete(id);
    }

    @Transactional
    public SubCrop save(SubCrop subCrop) {
        return getRepository().save(subCrop);
    }

    @Transactional
    public SubCrop save(SubCropModel model) {

        SubCrop subCrop;

        if (model.getId() == null) {
            subCrop = new SubCrop();
        }
        else {
            subCrop = getRepository().findOne(model.getId());
        }

        subCrop.setNameRo(model.getNameRo());
        subCrop.setNameRu(model.getNameRu());
        subCrop.setDescriptionRo(model.getDescriptionRo());
        subCrop.setDescriptionRu(model.getDescriptionRu());
        subCrop.setCrop(cropService.findOne(model.getCropId()));

        return save(subCrop);
    }

    @Override
    public SubCropRepository getRepository() {
        return subCropRepository;
    }
}
