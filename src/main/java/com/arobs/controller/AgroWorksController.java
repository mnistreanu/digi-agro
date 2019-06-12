package com.arobs.controller;

import com.arobs.entity.agro.AgroWork;
import com.arobs.entity.agro.AgroWorkType;
import com.arobs.entity.crop.Crop;
import com.arobs.enums.UnitOfMeasure;
import com.arobs.model.PayloadModel;
import com.arobs.model.agrowork.OtherWorksModel;
import com.arobs.service.agrowork.AgroWorkService;
import com.arobs.service.agrowork.AgroWorkTypeService;
import com.arobs.service.crop.CropService;
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
                for (AgroWorkType workType : typeList) {
                    OtherWorksModel child = new OtherWorksModel();
                    child.setWorkTypeId(workType.getId());
                    child.setWorkTypeNameRo(workType.getNameRo());
                    child.setWorkTypeNameRu(workType.getNameRu());
                    child.setQuantity(Math.random() * 100);
                    child.setUnitOfMeasure(UnitOfMeasure.Tone.getUnitOfMeasure());
                    parent.getChildren().add(child);
                }
            }

            for (AgroWork agroWork : agroWorks) {
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


        payloadModel.setPayload(list);
        payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
        return ResponseEntity.ok(payloadModel);
    }
}
