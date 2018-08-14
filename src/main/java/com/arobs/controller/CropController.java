package com.arobs.controller;

import com.arobs.entity.Crop;
import com.arobs.entity.CropCategory;
import com.arobs.entity.CropVariety;
import com.arobs.model.CropVarietyModel;
import com.arobs.model.ListItemModel;
import com.arobs.model.PayloadModel;
import com.arobs.service.CropCategoryService;
import com.arobs.service.CropService;
import com.arobs.service.CropVarietyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crops")
public class CropController {

    @Autowired
    private CropCategoryService cropCategoryService;

    @Autowired
    private CropService cropService;

    @Autowired
    private CropVarietyService cropVarietyService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> getCrops(@RequestParam(value = "page_no", required = false) final Integer page,
                                               @RequestParam(value = "rows_per_page", required = false) final Integer rowsPerPage,
                                               @RequestParam(value = "sort", required = false) final List<String> sorts,
                                               @RequestParam(value = "filter", required = false) final List<String> filters) {

        if (page != null && rowsPerPage != null && sorts != null && filters != null) {
            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, filters, sorts).toString());
        }

        if (page != null && rowsPerPage != null && filters != null) {
            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, filters, new ArrayList<>()).toString());
        }

        if (page != null && rowsPerPage != null) {
            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, new ArrayList<>(), new ArrayList<>()).toString());
        }

        return ResponseEntity.ok(cropService.findAll().toString());
    }


    @RequestMapping(value = "/categories/select_items", method = RequestMethod.GET)
    public ResponseEntity<List<ListItemModel>> getCropCategories() {
        return ResponseEntity.ok(cropCategoryService.fetchItems());
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
