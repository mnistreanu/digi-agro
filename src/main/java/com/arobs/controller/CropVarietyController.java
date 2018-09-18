package com.arobs.controller;


import com.arobs.entity.CropVariety;
import com.arobs.model.CropVarietyModel;
import com.arobs.model.ListItemModel;
import com.arobs.service.crop.CropService;
import com.arobs.service.crop.CropVarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crop_varieties")
public class CropVarietyController {

    @Autowired
    private CropVarietyService cropVarietyService;

    @Autowired
    private CropService cropService;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        cropVarietyService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CropVarietyModel> save(@RequestBody CropVarietyModel model) {
        return ResponseEntity.ok(new CropVarietyModel(cropVarietyService.save(model)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CropVarietyModel> findById(@PathVariable("id") final Long id) {

        CropVariety cropVariety = cropVarietyService.findOne(id);

        if (cropVariety != null) {
            return ResponseEntity.ok(new CropVarietyModel(cropVariety));
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CropVarietyModel> update(@PathVariable("id") final Long id, @RequestBody final CropVarietyModel resource) {

        CropVariety cropVariety = cropVarietyService.findOne(id);

        if (cropVariety == null) {
            return ResponseEntity.notFound().build();
        }

        resource.setId(id);
        return ResponseEntity.ok(new CropVarietyModel(cropVarietyService.save(resource)));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> getCrops(@RequestParam(value = "page_no", required = false) final Integer page,
                                           @RequestParam(value = "rows_per_page", required = false) final Integer rowsPerPage,
                                           @RequestParam(value = "sort", required = false) final List<String> sorts,
                                           @RequestParam(value = "filter", required = false) final List<String> filters) {

        if (page != null && rowsPerPage != null && sorts != null && filters != null) {
            return ResponseEntity.ok(cropVarietyService.findAll(page, rowsPerPage, filters, sorts).toString());
        }

        if (page != null && rowsPerPage != null && filters != null) {
            return ResponseEntity.ok(cropVarietyService.findAll(page, rowsPerPage, filters, new ArrayList<>()).toString());
        }

        if (page != null && rowsPerPage != null) {
            return ResponseEntity.ok(cropVarietyService.findAll(page, rowsPerPage, new ArrayList<>(), new ArrayList<>()).toString());
        }

        return ResponseEntity.ok(cropVarietyService.findAll().toString());
    }

    @RequestMapping(value = "/crops/select_items", method = RequestMethod.GET)
    public ResponseEntity<List<ListItemModel>> getCropCategoryListItems() {
        return ResponseEntity.ok(cropService.fetchItems());
    }
}
