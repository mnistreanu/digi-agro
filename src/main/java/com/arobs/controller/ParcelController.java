package com.arobs.controller;

import com.arobs.entity.*;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.service.agrowork.AgroWorkService;
import com.arobs.service.agrowork.AgroWorkTypeService;
import com.arobs.service.crop.CropService;
import com.arobs.service.parcel.ParcelCropService;
import com.arobs.service.parcel.ParcelService;
import com.arobs.utils.StaticUtil;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parcel")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;
    @Autowired
    private ParcelCropService parcelCropService;
    @Autowired
    private CropService cropService;
    @Autowired
    private AgroWorkService agroWorkService;
    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ParcelModel> findOne(@PathVariable Long id) {

        // todo: refactor model creation...

        Parcel parcel = parcelService.findOne(id);
        ParcelModel model = new ParcelModel(parcel);

        ParcelCrop parcelCrop = parcelCropService.find(parcel.getId());
        Crop crop = cropService.findOne(parcelCrop.getCropId());
        model.setupCropInfo(parcelCrop, crop);

        AgroWork lastAgroWork = agroWorkService.findLast(parcelCrop.getId());
        if (lastAgroWork != null) {
            AgroWorkType agroWorkType = agroWorkTypeService.findOne(lastAgroWork.getWorkType().getId());
            model.setupLastCropWork(lastAgroWork, agroWorkType);
        }

        ParcelGeometry geometry = parcelService.findParcelGeometry(parcel.getId());
        // todo: simplify
        Type listType = new TypeToken<Map<String, List<BigDecimal[]>>>() {}.getType();
        Map<String, List<BigDecimal[]>> map = StaticUtil.gson.fromJson(geometry.getCoordinates(), listType);
        model.setCoordinates(map.get("coordinates"));

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ParcelModel>> find(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Parcel> parcels = parcelService.find(tenantId);
        List<ParcelModel> models = new ArrayList<>();

        for (Parcel parcel : parcels) {
            ParcelModel model = new ParcelModel(parcel);
            models.add(model);

            ParcelCrop parcelCrop = parcelCropService.find(parcel.getId());
            Crop crop = cropService.findOne(parcelCrop.getCropId());
            model.setupCropInfo(parcelCrop, crop);

            AgroWork lastAgroWork = agroWorkService.findLast(parcelCrop.getId());
            if (lastAgroWork != null) {
                AgroWorkType agroWorkType = agroWorkTypeService.findOne(lastAgroWork.getWorkType().getId());
                model.setupLastCropWork(lastAgroWork, agroWorkType);
            }

            ParcelGeometry geometry = parcelService.findParcelGeometry(parcel.getId());
            // todo: simplify
            Type listType = new TypeToken<Map<String, List<BigDecimal[]>>>() {}.getType();
            Map<String, List<BigDecimal[]>> map = StaticUtil.gson.fromJson(geometry.getCoordinates(), listType);
            model.setCoordinates(map.get("coordinates"));
        }

        return ResponseEntity.ok(models);
    }


}
