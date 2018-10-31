package com.arobs.service.crop;

import com.arobs.entity.Crop;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.crop.CropModel;
import com.arobs.repository.CropRepository;
import com.arobs.repository.custom.CropCustomRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CropService implements HasRepository<CropRepository> {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private CropCustomRepository cropCustomRepository;

    @Override
    public CropRepository getRepository() {
        return cropRepository;
    }

    public List<Crop> find(Long categoryId) {
        if (categoryId != null) {
            return getRepository().find(categoryId);
        } else {
            return getRepository().find();
        }
    }

    public Crop findOne(Long id) {
        return getRepository().findOne(id);
    }

    public Page<Crop> findAll(int page, int size) {
        return  cropRepository.findAll(new PageRequest(page, size));
    }

    public JSONObject findAll() {

        Iterable<Crop> iterable = cropRepository.findAll();
        List<Crop> crops = new ArrayList<>();
        iterable.forEach(crops::add);

        JSONObject response = new JSONObject();

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(crops, new TypeToken<List<Crop>>() {}.getType());

        try {
            response.put("total_count", crops.size());
            response.put("items", element.getAsJsonArray());
            response.put("page", 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }

    public JSONObject findAll(int page, int size, List<String> filters, List<String> sorts) {


        List<Crop> crops = cropCustomRepository.findByFilter(page, size, filters, sorts);
        List<CropModel> models = crops.stream().map(CropModel::new).collect(Collectors.toList());
        long totalFilteredCrops = cropCustomRepository.countFiltered(filters);

        JSONObject response = new JSONObject();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(models, new TypeToken<List<CropModel>>() {}.getType());

        try {
            response.put("total_count", totalFilteredCrops);
            response.put("items", element.getAsJsonArray());
            response.put("page", page);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Transactional
    public Crop save(CropModel model) {

        Crop crop;

        if (model.getId() == null) {
            crop = new Crop();
        }
        else {
            crop = getRepository().findOne(model.getId());
        }

        crop.setCropCategoryId(model.getCropCategoryId());
        crop.setNameRo(model.getNameRo());
        crop.setNameRu(model.getNameRu());

        return getRepository().save(crop);
    }

    @Transactional
    public void delete(Long id) {
        getRepository().delete(id);
    }

}
