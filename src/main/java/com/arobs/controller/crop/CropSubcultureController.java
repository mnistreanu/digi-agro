package com.arobs.controller.crop;


import com.arobs.entity.CropSubculture;
import com.arobs.model.PayloadModel;
import com.arobs.model.crop.CropSubcultureModel;
import com.arobs.service.crop.CropService;
import com.arobs.service.crop.CropSubcultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crop-subculture")
public class CropSubcultureController {

    @Autowired
    private CropSubcultureService subCropService;
    @Autowired
    private CropService cropService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CropSubcultureModel> findById(@PathVariable("id") final Long id) {

        CropSubculture cropSubculture = subCropService.findOne(id);

        if (cropSubculture != null) {
            return ResponseEntity.ok(new CropSubcultureModel(cropSubculture));
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getSubCrops() {
        PayloadModel<CropSubcultureModel> payloadModel = new PayloadModel<>();

        try {
            List<CropSubculture> subcultures = subCropService.find();
            if (!subcultures.isEmpty()) {
                List<CropSubcultureModel> models = subcultures.stream().map(CropSubcultureModel::new).collect(Collectors.toList());
                CropSubcultureModel[] payload = models.toArray(new CropSubcultureModel[models.size()]);
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


    @RequestMapping(value = "/by-crop/{id}", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getSubCrops(@PathVariable("id") Long cropId) {
        PayloadModel<CropSubcultureModel> payloadModel = new PayloadModel<>();

        try {
            List<CropSubculture> varieties = subCropService.find(cropId);
            if (!varieties.isEmpty()) {
                List<CropSubcultureModel> models = varieties.stream().map(CropSubcultureModel::new).collect(Collectors.toList());
                CropSubcultureModel[] payload = models.toArray(new CropSubcultureModel[models.size()]);
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

     @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CropSubcultureModel> save(@RequestBody CropSubcultureModel model) {
        return ResponseEntity.ok(new CropSubcultureModel(subCropService.save(model)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        subCropService.delete(id);
    }
}
