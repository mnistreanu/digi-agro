package com.arobs.controller;

import com.arobs.entity.AgroWork;
import com.arobs.entity.AgroWorkType;
import com.arobs.entity.Crop;
import com.arobs.enums.UnitOfMeasure;
import com.arobs.model.PayloadModel;
import com.arobs.model.agrowork.OtherWorksModel;
import com.arobs.service.crop.CropService;
import com.arobs.service.agrowork.AgroWorkService;
import com.arobs.service.agrowork.AgroWorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agroworks")
public class AgroWorksController {

    @Autowired
    private AgroWorkService agroWorkService;

    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @Autowired
    private CropService cropService;

//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<CropModel> findById(@PathVariable("id") final Long id) {
//
//        Crop crop = cropService.findOne(id);
//
//        if (crop != null) {
//            return ResponseEntity.ok(new CropModel(crop));
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void remove(@PathVariable Long id) {
//        cropService.delete(id);
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public ResponseEntity<String> getCrops(@RequestParam(value = "page_no", required = false) final Integer page,
//                                               @RequestParam(value = "rows_per_page", required = false) final Integer rowsPerPage,
//                                               @RequestParam(value = "sort", required = false) final List<String> sorts,
//                                               @RequestParam(value = "filter", required = false) final List<String> filters) {
//
//        if (page != null && rowsPerPage != null && sorts != null && filters != null) {
//            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, filters, sorts).toString());
//        }
//
//        if (page != null && rowsPerPage != null && filters != null) {
//            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, filters, new ArrayList<>()).toString());
//        }
//
//        if (page != null && rowsPerPage != null) {
//            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, new ArrayList<>(), new ArrayList<>()).toString());
//        }
//
//        return ResponseEntity.ok(cropService.findAll().toString());
//    }
//
//
//    @RequestMapping(value = "/categories", method = RequestMethod.GET)
//    public ResponseEntity<PayloadModel> getCropCategories() {
//        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();
//
//        try {
//            List<CropCategory> categories = agroWorkService.find();
//            if (!categories.isEmpty()) {
//                List<CropVarietyModel> models = categories.stream().map(CropVarietyModel::new).collect(Collectors.toList());
//                CropVarietyModel[] payload = models.toArray(new CropVarietyModel[models.size()]);
//                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
//                payloadModel.setPayload(payload);
//            } else {
//                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
//            }
//        } catch (Exception e) {
//            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
//            payloadModel.setMessage(e.getLocalizedMessage());
//        }
//
//        return ResponseEntity.ok(payloadModel);
//    }
//
//    @RequestMapping(value = "/categories/select_items", method = RequestMethod.GET)
//    public ResponseEntity<List<ListItemModel>> getCropCategoryListItems() {
//        return ResponseEntity.ok(agroWorkService.fetchItems());
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<CropModel> save(@RequestBody CropModel model) {
//        return ResponseEntity.ok(new CropModel(cropService.save(model)));
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<CropModel> update(@PathVariable("id") final Long id, @RequestBody final CropModel resource) {
//        Crop crop = cropService.findOne(id);
//
//        if (crop == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(new CropModel(cropService.save(resource)));
//    }
//
//    @RequestMapping(value = "/crops", method = RequestMethod.GET)
//    public ResponseEntity<PayloadModel> getCrops(@RequestParam("categoryId") Long categoryId) {
//        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();
//
//        try {
//            List<Crop> crops = cropService.find(categoryId == null ? 1L : categoryId);
//            if (!crops.isEmpty()) {
//                List<CropVarietyModel> models = crops.stream().map(CropVarietyModel::new).collect(Collectors.toList());
//                CropVarietyModel[] payload = models.toArray(new CropVarietyModel[models.size()]);
//                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
//                payloadModel.setPayload(payload);
//            } else {
//                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
//            }
//        } catch (Exception e) {
//            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
//            payloadModel.setMessage(e.getLocalizedMessage());
//        }
//
//        return ResponseEntity.ok(payloadModel);
//    }
//
//    @RequestMapping(value = "/varieties", method = RequestMethod.GET)
//    public ResponseEntity<PayloadModel> getCropVarieties(@RequestParam("cropId") Long cropId) {
//        PayloadModel<CropVarietyModel> payloadModel = new PayloadModel<>();
//
//        try {
//            List<CropVariety> varieties = cropVarietyService.find(cropId);
//            if (!varieties.isEmpty()) {
//                List<CropVarietyModel> models = varieties.stream().map(CropVarietyModel::new).collect(Collectors.toList());
//                CropVarietyModel[] payload = models.toArray(new CropVarietyModel[models.size()]);
//                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
//                payloadModel.setPayload(payload);
//            } else {
//                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
//            }
//        } catch (Exception e) {
//            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
//            payloadModel.setMessage(e.getLocalizedMessage());
//        }
//
//        return ResponseEntity.ok(payloadModel);
//    }

    @RequestMapping(value = "/others-tree", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getOtherWorksTree() {
        List<OtherWorksModel> list = new ArrayList<>();

        PayloadModel<OtherWorksModel> payloadModel = new PayloadModel<>();

        List<Crop> crops = cropService.find(null);
        for (Crop crop : crops) {
            OtherWorksModel parent = new OtherWorksModel();
            parent.setCropId(crop.getId());
            parent.setCropNameRo(crop.getNameRo());
            parent.setCropNameRu(crop.getNameRu());
            parent.setChildren(new ArrayList<>());
            list.add(parent);

            List<AgroWork> agroWorks = agroWorkService.find(crop.getId());
            if (agroWorks.isEmpty()) {
                List<AgroWorkType> typeList = agroWorkTypeService.find();
                for (AgroWorkType workType: typeList) {
                    OtherWorksModel child = new OtherWorksModel();
                    child.setWorkTypeId(workType.getId());
                    child.setWorkTypeNameRo(workType.getNameRo());
                    child.setWorkTypeNameRu(workType.getNameRu());
                    child.setQuantity(Math.random() * 100);
                    child.setUnitOfMeasure(UnitOfMeasure.Tone.getUnitOfMeasure());
                    parent.getChildren().add(child);
                }
            }

            for (AgroWork agroWork: agroWorks) {
                OtherWorksModel child = new OtherWorksModel();
                AgroWorkType workType = agroWork.getWorkType();
                child.setWorkTypeId(workType.getId());
                child.setWorkTypeNameRo(workType.getNameRo());
                child.setWorkTypeNameRu(workType.getNameRu());
                child.setQuantity(Math.random() * 100);
                child.setUnitOfMeasure(UnitOfMeasure.Tone.getUnitOfMeasure());
                parent.getChildren().add(child);
            }
        }


        OtherWorksModel[] arr = new OtherWorksModel[list.size()];
        payloadModel.setPayload(list.toArray(arr));
        payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
        return ResponseEntity.ok(payloadModel);
    }
}
