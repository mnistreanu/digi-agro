package com.arobs.controller.parcel;

import com.arobs.model.parcel.ParcelCropSeasonModel;
import com.arobs.service.parcel.ParcelHarvestSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parcel-harvest-summary")
public class ParcelHarvestSummaryController {

    @Autowired
    private ParcelHarvestSummaryService phsService;

    @RequestMapping(value = "/{harvestYear}", method = RequestMethod.GET)
    public ResponseEntity<ParcelCropSeasonModel> find(@PathVariable Integer harvestYear, @PathVariable Long parcelId) {
        ParcelCropSeasonModel model =  new ParcelCropSeasonModel();
//        ParcelCropSeason pcs = phsService.find(parcelId, harvestYear);
//        if (pcs != null) {
//            model = new ParcelCropSeasonModel(pcs);
//        }

        return ResponseEntity.ok(model);
    }

}
