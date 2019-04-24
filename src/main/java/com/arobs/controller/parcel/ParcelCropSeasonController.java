package com.arobs.controller.parcel;

import com.arobs.entity.ParcelCropSeason;
import com.arobs.model.parcel.ParcelCropSeasonModel;
import com.arobs.service.parcel.ParcelCropSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parcel-crop-season")
public class ParcelCropSeasonController {

    @Autowired
    private ParcelCropSeasonService pcsService;

    @RequestMapping(value = "/last/{parcelId}", method = RequestMethod.GET)
    public ResponseEntity<ParcelCropSeasonModel> findLast(@PathVariable Long parcelId) {
        ParcelCropSeason pcs = pcsService.findLast(parcelId);
        ParcelCropSeasonModel model = new ParcelCropSeasonModel(pcs);

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/{harvestYear}/{parcelId}", method = RequestMethod.GET)
    public ResponseEntity<ParcelCropSeasonModel> find(@PathVariable Integer harvestYear, @PathVariable Long parcelId) {
        ParcelCropSeasonModel model =  new ParcelCropSeasonModel();
        ParcelCropSeason pcs = pcsService.find(parcelId, harvestYear);
        if (pcs != null) {
            model = new ParcelCropSeasonModel(pcs);
        }

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/{harvestYear}", method = RequestMethod.GET)
    public ResponseEntity<List<ParcelCropSeasonModel>> find(HttpSession session, @PathVariable Integer harvestYear) {
        Long tenantId = (Long) session.getAttribute("tenant");
        List<ParcelCropSeasonModel> models = new ArrayList<>();
        List<ParcelCropSeason> list = pcsService.findByTenant(tenantId, harvestYear);

        for (ParcelCropSeason pcs : list) {
            models.add(new ParcelCropSeasonModel(pcs));
        }

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/all/{parcelId}", method = RequestMethod.GET)
    public ResponseEntity<List<ParcelCropSeasonModel>> findAll(@PathVariable Long parcelId) {
        List<ParcelCropSeasonModel> models = new ArrayList<>();

        List<ParcelCropSeason> list = pcsService.find(parcelId);
        for (ParcelCropSeason pcs : list) {
            models.add(new ParcelCropSeasonModel(pcs));
        }

        return ResponseEntity.ok(models);
    }

    //
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ResponseEntity<List<ParcelCropSeasonModel>> find(HttpSession session) {
//        Long tenantId = (Long) session.getAttribute("tenant");
//
//        List<ParcelCropSeasonModel> parcels = pcsService.find(tenantId);
//        List<ParcelModel> models = new ArrayList<>();
//
//        for (Parcel parcel : parcels) {
//            ParcelModel model = pcsService.getModel(parcel);
//            models.add(model);
//        }
//
//        return ResponseEntity.ok(models);
//    }
//
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ParcelCropSeasonModel> save(@RequestBody ParcelCropSeasonModel model, HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        ParcelCropSeason parcel = pcsService.save(model);
        model = pcsService.getModel(parcel);

        return ResponseEntity.ok(model);
    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void remove(@PathVariable Long id) {
//        pcsService.remove(id);
//    }

}
