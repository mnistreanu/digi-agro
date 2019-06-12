package com.arobs.service.crop;

import com.arobs.entity.crop.CropVariety;
import com.arobs.model.crop.CropVarietyModel;
import com.arobs.repository.CropVarietyRepository;
import com.arobs.repository.custom.CropVarietyCustomRepository;
import com.arobs.service.BaseEntityService;
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
public class CropVarietyService extends BaseEntityService<CropVariety, CropVarietyRepository> {

    @Autowired
    private CropVarietyRepository cropVarietyRepository;

    @Autowired
    private CropVarietyCustomRepository cropVarietyCustomRepository;

    @Autowired
    private CropService cropService;

    @Autowired
    private CropSubcultureService cropSubcultureService;

    @Override
    public CropVarietyRepository getRepository() {
        return cropVarietyRepository;
    }

    public List<CropVariety> findByCrop(Long cropId) {
        return getRepository().findByCrop(cropId);
    }

    public List<CropVariety> findBySubculture(Long cropSubcultureId) {
        return getRepository().findBySubculture(cropSubcultureId);
    }

    @Transactional
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
        if (model.getCropId() != null) {
            cropVariety.setCrop(cropService.getOne(model.getCropId()));
        }
        if (model.getCropSubcultureId() != null) {
            cropVariety.setCropSubculture(cropSubcultureService.getOne(model.getCropSubcultureId()));
        }

        return save(cropVariety);
    }

    public JSONObject find() {

        Collection<CropVariety> cropVarieties = getRepository().findAll();
        List<CropVarietyModel> models = cropVarieties.stream().map(CropVarietyModel::new).collect(Collectors.toList());

        JSONObject response = new JSONObject();

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(models, new TypeToken<List<CropVarietyModel>>() {
        }.getType());

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
        List<CropVarietyModel> models = cropVarieties.stream().map(CropVarietyModel::new).collect(Collectors.toList());
        long totalFilteredCropVarieties = cropVarietyCustomRepository.countFiltered(filters);

        JSONObject response = new JSONObject();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(models, new TypeToken<List<CropVarietyModel>>() {
        }.getType());

        try {
            response.put("total_count", totalFilteredCropVarieties);
            response.put("items", element.getAsJsonArray());
            response.put("page", page);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }
}
