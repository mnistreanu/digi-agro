package com.arobs.service;

import com.arobs.entity.Crop;
import com.arobs.entity.CropVariety;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.CropVarietyModel;
import com.arobs.repository.CropVarietyRepository;
import com.arobs.repository.custom.CropVarietyCustomRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropVarietyService implements HasRepository<CropVarietyRepository> {

    @Autowired
    private CropVarietyRepository cropVarietyRepository;

    @Autowired
    private CropVarietyCustomRepository cropVarietyCustomRepository;

    public List<CropVariety> find(Long cropId) {
        return getRepository().find(cropId);
    }

    public CropVariety findOne(Long id) {
        return getRepository().findOne(id);
    }

    public void delete(Long id) {
        getRepository().delete(id);
    }

    public CropVariety save(CropVariety cropVariety) {
        return getRepository().save(cropVariety);
    }

    public CropVariety save(CropVarietyModel model) {

        CropVariety cropVariety;

        if (model.getId() == null) {
            cropVariety = new CropVariety();
        } else {
            cropVariety = getRepository().findOne(model.getId());
        }

        cropVariety.setNameRo(model.getNameRo());
        cropVariety.setNameRu(model.getNameRu());
        cropVariety.setDescriptionRo(model.getDescriptionRo());
        cropVariety.setDescriptionRu(model.getDescriptionRu());
        cropVariety.setCropId(model.getCropId());
        cropVariety.setSeedConsumptionHa(model.getSeedConsumptionHa());
        cropVariety.setUnitOfMeasure(model.getUnitOfMeasure());

        return getRepository().save(cropVariety);
    }

    public JSONObject findAll() {

        Iterable<CropVariety> iterable = getRepository().findAll();
        List<CropVariety> cropVarieties = new ArrayList<>();
        iterable.forEach(cropVarieties::add);

        JSONObject response = new JSONObject();

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(cropVarieties, new TypeToken<List<Crop>>() {}.getType());

        try {
            response.put("total_count", cropVarieties.size());
            response.put("items", element.getAsJsonArray());
            response.put("page", 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }

    public JSONObject findAll(int page, int size, List<String> filters, List<String> sorts) {


        List<CropVariety> cropVarieties = cropVarietyCustomRepository.findByFilter(page, size, filters, sorts);
        long totalFilteredCropVarieties = cropVarietyCustomRepository.countFiltered(filters);

        JSONObject response = new JSONObject();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(cropVarieties, new TypeToken<List<Crop>>() {}.getType());

        try {
            response.put("total_count", totalFilteredCropVarieties);
            response.put("items", element.getAsJsonArray());
            response.put("page", page);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public CropVarietyRepository getRepository() {
        return cropVarietyRepository;
    }
}
