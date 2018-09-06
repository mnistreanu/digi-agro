package com.arobs.controller;

import com.arobs.entity.Brand;
import com.arobs.model.BrandModel;
import com.arobs.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<BrandModel>> getModels() {

        List<Brand> items = brandService.findAll();
        List<BrandModel> models = items.stream().map(BrandModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BrandModel> getModel(@PathVariable Long id) {
        Brand brand = brandService.findOne(id);
        return ResponseEntity.ok(new BrandModel(brand));
    }

    @RequestMapping(value = "/unique", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUnique(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam("field") String field, @RequestParam("value") String value) {
        return ResponseEntity.ok(brandService.isUnique(id, field, value));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BrandModel> save(@RequestBody BrandModel model) {
        return ResponseEntity.ok(new BrandModel(brandService.save(model)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        brandService.remove(id);
    }

}
