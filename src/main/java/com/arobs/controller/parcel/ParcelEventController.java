package com.arobs.controller.parcel;

import com.arobs.entity.parcel.ParcelEvent;
import com.arobs.model.parcel.ParcelEventModel;
import com.arobs.service.parcel.ParcelEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parcel-event")
public class ParcelEventController {

    @Autowired
    private ParcelEventService parcelEventService;

    @RequestMapping(value = "/{parcelId}", method = RequestMethod.GET)
    public ResponseEntity<List<ParcelEventModel>> find(@PathVariable Long parcelId) {

        List<ParcelEvent> events = parcelEventService.find(parcelId);
        List<ParcelEventModel> models = events.stream().map(ParcelEventModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ParcelEventModel> save(@RequestBody ParcelEventModel model) {

        ParcelEvent event = parcelEventService.save(model);
        model = new ParcelEventModel(event);

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        parcelEventService.remove(id);
    }

}
