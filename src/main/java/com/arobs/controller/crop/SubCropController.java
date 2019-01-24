package com.arobs.controller.crop;


import com.arobs.entity.SubCrop;
import com.arobs.model.PayloadModel;
import com.arobs.model.crop.SubCropModel;
import com.arobs.service.crop.CropService;
import com.arobs.service.crop.SubCropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sub-crop")
public class SubCropController {

    @Autowired
    private SubCropService subCropService;
    @Autowired
    private CropService cropService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SubCropModel> findById(@PathVariable("id") final Long id) {

        SubCrop subCrop = subCropService.findOne(id);

        if (subCrop != null) {
            return ResponseEntity.ok(new SubCropModel(subCrop));
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/by-crop/{id}", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getSubCrops(@PathVariable("id") Long cropId) {
        PayloadModel<SubCropModel> payloadModel = new PayloadModel<>();

        try {
            List<SubCrop> varieties = subCropService.find(cropId);
            if (!varieties.isEmpty()) {
                List<SubCropModel> models = varieties.stream().map(SubCropModel::new).collect(Collectors.toList());
                SubCropModel[] payload = models.toArray(new SubCropModel[models.size()]);
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
    public ResponseEntity<SubCropModel> save(@RequestBody SubCropModel model) {
        return ResponseEntity.ok(new SubCropModel(subCropService.save(model)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        subCropService.delete(id);
    }
}
