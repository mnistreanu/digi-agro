package com.arobs.controller;

import com.arobs.entity.MapEvent;
import com.arobs.model.MapEventModel;
import com.arobs.model.UpdateFieldModel;
import com.arobs.service.AuthService;
import com.arobs.service.MapEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/map-event")
public class MapEventController {

    @Autowired
    private MapEventService mapEventService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<MapEventModel>> find() {
        Long userId = authService.getCurrentUser().getId();
        List<MapEvent> mapEvents = mapEventService.find(userId);
        List<MapEventModel> models = mapEvents.stream().map(MapEventModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MapEventModel> save(@RequestBody MapEventModel model) {
        Long userId = authService.getCurrentUser().getId();
        return ResponseEntity.ok(new MapEventModel(mapEventService.save(model, userId)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        mapEventService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody UpdateFieldModel model) {
        mapEventService.update(model);
    }

}
