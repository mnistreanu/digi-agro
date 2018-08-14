package com.arobs.service;

import com.arobs.entity.Crop;
import com.arobs.entity.Tenant;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.CropModel;
import com.arobs.model.tenant.TenantModel;
import com.arobs.repository.CropRepository;
import com.arobs.repository.custom.CropCustomRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.tools.tree.BooleanExpression;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CropService implements HasRepository<CropRepository> {

    public static final String SORT_DESC = "DESC";

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private CropCustomRepository cropCustomRepository;

    public List<Crop> find(Long categoryId) {
        if (categoryId != null) {
            return getRepository().find(categoryId);
        } else {
            return getRepository().find();
        }
    }

    @Override
    public CropRepository getRepository() {
        return cropRepository;
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
        long totalFilteredCrops = cropCustomRepository.countFiltered(filters);

        JSONObject response = new JSONObject();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(crops, new TypeToken<List<Crop>>() {}.getType());

        try {
            response.put("total_count", totalFilteredCrops);
            response.put("items", element.getAsJsonArray());
            response.put("page", page);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }

    public Crop save(CropModel model) {

        Crop crop;

        if (model.getId() == null) {
            crop = new Crop();
        } else {
            crop = getRepository().findOne(model.getId());
        }

        crop.setCropCategoryId(model.getCropCategoryId());
        crop.setNameRo(model.getNameRo());
        crop.setNameRu(model.getNameRu());

        return getRepository().save(crop);
    }

    public Crop findOne(Long id) {
        return getRepository().findOne(id);
    }

    public void delete(Long id) {
        getRepository().delete(id);
    }




}
