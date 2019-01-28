package com.arobs.controller;

import com.arobs.entity.Parcel;
import com.arobs.model.parcel.ParcelModel;
import com.arobs.service.parcel.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parcel")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ParcelModel> findOne(@PathVariable Long id) {

        Parcel parcel = parcelService.findOne(id);
        ParcelModel model = parcelService.getModel(parcel);

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<ParcelModel>> find(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<Parcel> parcels = parcelService.find(tenantId);
        List<ParcelModel> models = new ArrayList<>();

        for (Parcel parcel : parcels) {
            ParcelModel model = parcelService.getModel(parcel);
            models.add(model);
        }

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ParcelModel> save(@RequestBody ParcelModel model, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        Parcel parcel = parcelService.save(model, tenantId);
        model = parcelService.getModel(parcel);

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        parcelService.remove(id);
    }

}
