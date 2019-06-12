package com.arobs.controller;

import com.arobs.entity.EventType;
import com.arobs.model.EventTypeModel;
import com.arobs.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event-type")
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public ResponseEntity<List<EventTypeModel>> getTree(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<EventTypeModel> models = new ArrayList<>();
        Map<Long, EventTypeModel> parentMap = new HashMap<>();

        List<EventType> events = eventTypeService.find(tenantId);

        // process parents
        for (EventType event : events) {
            if (event.getParent() != null) {
                continue;
            }

            EventTypeModel model = new EventTypeModel(event);
            parentMap.put(event.getId(), model);
            models.add(model);
        }

        // process children
        for (EventType event : events) {
            if (event.getParent() == null) {
                continue;
            }
            EventTypeModel model = new EventTypeModel(event);
            EventTypeModel parent = parentMap.get(event.getParent().getId());
            if (parent == null) {
                continue;
            }
            if (parent.getChildren() == null) {
                parent.setChildren(new ArrayList<>());
            }
            parent.getChildren().add(model);
        }

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/roots", method = RequestMethod.GET)
    public ResponseEntity<List<EventTypeModel>> getRoots(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        List<EventType> roots = eventTypeService.findRoots(tenantId);
        List<EventTypeModel> models = roots.stream().map(EventTypeModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EventTypeModel> getModel(@PathVariable Long id) {
        EventType event = eventTypeService.findOne(id);
        return ResponseEntity.ok(new EventTypeModel(event));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<EventType> save(@RequestBody EventTypeModel model,
                                          HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(eventTypeService.save(model, tenantId));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        eventTypeService.delete(id);
    }

}
