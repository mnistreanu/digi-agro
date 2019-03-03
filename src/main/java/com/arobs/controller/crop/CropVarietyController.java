package com.arobs.controller.crop;


import com.arobs.entity.Crop;
import com.arobs.entity.CropCategory;
import com.arobs.entity.CropVariety;
import com.arobs.model.PayloadModel;
import com.arobs.model.crop.CropVarietyModel;
import com.arobs.model.crop.CropVarietyTreeModel;
import com.arobs.service.crop.CropCategoryService;
import com.arobs.service.crop.CropService;
import com.arobs.service.crop.CropVarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crop-variety")
public class CropVarietyController {

    @Autowired
    private CropVarietyService cropVarietyService;
    @Autowired
    private CropService cropService;
    @Autowired
    private CropCategoryService cropCategoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CropVarietyModel> findById(@PathVariable("id") final Long id) {

        CropVariety cropVariety = cropVarietyService.findOne(id);

        if (cropVariety != null) {
            return ResponseEntity.ok(new CropVarietyModel(cropVariety));
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/by-crop/{id}", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCropVarieties(@PathVariable("id") Long cropId) {
        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();

        try {
            List<CropVariety> varieties = cropVarietyService.find(cropId);
            if (!varieties.isEmpty()) {
                List<CropVarietyModel> models = varieties.stream().map(CropVarietyModel::new).collect(Collectors.toList());
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(models);
            } else {
                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
            }
        } catch (Exception e) {
            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
            payloadModel.setMessage(e.getLocalizedMessage());
        }

        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> getCrops(@RequestParam(value = "page_no", required = false) final Integer page,
                                           @RequestParam(value = "rows_per_page", required = false) final Integer rowsPerPage,
                                           @RequestParam(value = "sort", required = false) final List<String> sorts,
                                           @RequestParam(value = "filter", required = false) final List<String> filters) {

        if (page != null && rowsPerPage != null && sorts != null && filters != null) {
            return ResponseEntity.ok(cropVarietyService.findAll(page, rowsPerPage, filters, sorts).toString());
        }

        if (page != null && rowsPerPage != null && filters != null) {
            return ResponseEntity.ok(cropVarietyService.findAll(page, rowsPerPage, filters, new ArrayList<>()).toString());
        }

        if (page != null && rowsPerPage != null) {
            return ResponseEntity.ok(cropVarietyService.findAll(page, rowsPerPage, new ArrayList<>(), new ArrayList<>()).toString());
        }

        return ResponseEntity.ok(cropVarietyService.findAll().toString());
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCropsTree() {
        List<CropVarietyTreeModel> models = new ArrayList<>();

        PayloadModel<CropVarietyTreeModel> payloadModel = new PayloadModel<>();

        List<CropCategory> categories = cropCategoryService.find();
        for (CropCategory category : categories) {
            CropVarietyTreeModel categoryModel = new CropVarietyTreeModel(category);
            models.add(categoryModel);

            List<Crop> crops = cropService.find(category.getId());
            if (!crops.isEmpty()) {
                categoryModel.setChildren(new ArrayList<>());
            }

            for (Crop crop : crops) {
                CropVarietyTreeModel cropModel = new CropVarietyTreeModel(crop);
                categoryModel.getChildren().add(cropModel);

                List<CropVariety> varieties = cropVarietyService.find(crop.getId());
                if (!varieties.isEmpty()) {
                    cropModel.setChildren(new ArrayList<>());
                }

                for (CropVariety variety : varieties) {
                    cropModel.getChildren().add(new CropVarietyTreeModel(variety));
                }
            }
        }

        payloadModel.setPayload(models);

        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CropVarietyModel> save(@RequestBody CropVarietyModel model) {
        return ResponseEntity.ok(new CropVarietyModel(cropVarietyService.save(model)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        cropVarietyService.delete(id);
    }
}
