package com.arobs.controller;

import com.arobs.entity.Crop;
import com.arobs.entity.CropCategory;
import com.arobs.entity.CropVariety;
import com.arobs.model.CropModel;
import com.arobs.model.CropVarietyModel;
import com.arobs.model.ListItemModel;
import com.arobs.model.PayloadModel;
import com.arobs.service.crop.CropCategoryService;
import com.arobs.service.crop.CropService;
import com.arobs.service.crop.CropVarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CropModel>> find() {
        List<Crop> crops = cropService.find(null);
        List<CropModel> models = crops.stream().map(CropModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CropModel> findById(@PathVariable("id") final Long id) {

        Crop crop = cropService.findOne(id);

        if (crop != null) {
            return ResponseEntity.ok(new CropModel(crop));
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        cropService.delete(id);
    }

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

    @RequestMapping(value = "/categories/select_items", method = RequestMethod.GET)
    public ResponseEntity<List<ListItemModel>> getCropCategoryListItems() {
        return ResponseEntity.ok(cropCategoryService.fetchItems());
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CropModel> save(@RequestBody CropModel model) {
        return ResponseEntity.ok(new CropModel(cropService.save(model)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CropModel> update(@PathVariable("id") final Long id, @RequestBody final CropModel resource) {
        Crop crop = cropService.findOne(id);

        if (crop == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CropModel(cropService.save(resource)));
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
