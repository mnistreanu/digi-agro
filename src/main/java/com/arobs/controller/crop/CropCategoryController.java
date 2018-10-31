package com.arobs.controller.crop;

import com.arobs.entity.CropCategory;
import com.arobs.model.crop.CropCategoryModel;
import com.arobs.service.crop.CropCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crop-category")
public class CropCategoryController {

    @Autowired
    private CropCategoryService cropCategoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CropCategoryModel>> getCropCategories() {

        List<CropCategory> categories = cropCategoryService.find();
        List<CropCategoryModel> models = categories.stream().map(CropCategoryModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

}
