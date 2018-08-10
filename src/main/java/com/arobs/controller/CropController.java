package com.arobs.controller;

import com.arobs.entity.Crop;
import com.arobs.entity.CropCategory;
import com.arobs.entity.CropVariety;
import com.arobs.model.CropVarietyModel;
import com.arobs.model.PayloadModel;
import com.arobs.service.CropCategoryService;
import com.arobs.service.CropService;
import com.arobs.service.CropVarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crop")
public class CropController {

    @Autowired
    private CropCategoryService cropCategoryService;

    @Autowired
    private CropService cropService;

    @Autowired
    private CropVarietyService cropVarietyService;


    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCropCategories() {
        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();

        try {
            List<CropCategory> categories = cropCategoryService.find();
            if (!categories.isEmpty()) {
                List<CropVarietyModel> models = categories.stream().map(CropVarietyModel::new).collect(Collectors.toList());
                CropVarietyModel[] payload = models.toArray(new CropVarietyModel[models.size()]);
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(payload);
            } else {
                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
            }
        } catch (Exception e) {
            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
            payloadModel.setMessage(e.getLocalizedMessage());
        }

        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(value = "/crops", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCrops(@RequestParam("categoryId") Long categoryId) {
        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();

        try {
            List<Crop> crops = cropService.find(categoryId == null ? 1L : categoryId);
            if (!crops.isEmpty()) {
                List<CropVarietyModel> models = crops.stream().map(CropVarietyModel::new).collect(Collectors.toList());
                CropVarietyModel[] payload = models.toArray(new CropVarietyModel[models.size()]);
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(payload);
            } else {
                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
            }
        } catch (Exception e) {
            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
            payloadModel.setMessage(e.getLocalizedMessage());
        }

        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(value = "/varieties", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCropVarieties(@RequestParam("cropId") Long cropId) {
        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();

        try {
            List<CropVariety> varieties = cropVarietyService.find(cropId);
            if (!varieties.isEmpty()) {
                List<CropVarietyModel> models = varieties.stream().map(CropVarietyModel::new).collect(Collectors.toList());
                CropVarietyModel[] payload = models.toArray(new CropVarietyModel[models.size()]);
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(payload);
            } else {
                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
            }
        } catch (Exception e) {
            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
            payloadModel.setMessage(e.getLocalizedMessage());
        }

        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCropsTree() {
        List<CropVarietyModel> l = new ArrayList<>();

        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();

        List<CropCategory> categories = cropCategoryService.find();
        for (CropCategory cat: categories){
            l.add(new CropVarietyModel(cat));

            List<Crop> crops = cropService.find(cat.getId());
            for (Crop crop: crops) {
                l.add(new CropVarietyModel(crop));

                List<CropVariety> varieties = cropVarietyService.find(crop.getId());
                for (CropVariety variety : varieties) {
                    l.add(new CropVarietyModel(variety));
                }
            }
        }

        CropVarietyModel[] arr = new CropVarietyModel[l.size()];
        payloadModel.setPayload(l.toArray(arr));

        return ResponseEntity.ok(payloadModel);
    }
}
