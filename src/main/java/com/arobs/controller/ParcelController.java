package com.arobs.controller;

import com.arobs.entity.Parcel;
import com.arobs.entity.ParcelGeometry;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.service.ParcelService;
import com.arobs.utils.StaticUtil;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ParcelModel>> find(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Parcel> parcels = parcelService.find(tenantId);
        List<ParcelModel> models = new ArrayList<>();

        for (Parcel parcel : parcels) {
            ParcelModel model = new ParcelModel(parcel);
            models.add(model);
            ParcelGeometry geometry = parcelService.findParcelGeometry(parcel.getId());
            // todo: simplify
            Type listType = new TypeToken<Map<String, List<BigDecimal[]>>>() {}.getType();
            Map<String, List<BigDecimal[]>> map = StaticUtil.gson.fromJson(geometry.getCoordinates(), listType);
            model.setCoordinates(map.get("coordinates"));
        }

        return ResponseEntity.ok(models);
    }


}
