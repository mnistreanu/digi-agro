package com.arobs.controller.crop;

import com.arobs.entity.Crop;
import com.arobs.model.PayloadModel;
import com.arobs.model.crop.CropModel;
import com.arobs.service.crop.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CropModel>> find() {
        List<Crop> crops = cropService.find(null);
        List<CropModel> models = crops.stream().map(CropModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CropModel> findById(@PathVariable("id") final Long id) {

        Crop crop = cropService.findOne(id);

        if (crop != null) {
            return ResponseEntity.ok(new CropModel(crop));
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CropModel> save(@RequestBody CropModel model) {
        return ResponseEntity.ok(new CropModel(cropService.save(model)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        cropService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getCrops(@RequestParam(value = "page_no", required = false) final Integer page,
                                               @RequestParam(value = "rows_per_page", required = false) final Integer rowsPerPage,
                                               @RequestParam(value = "sort", required = false) final List<String> sorts,
                                               @RequestParam(value = "filter", required = false) final List<String> filters) {

        if (page != null && rowsPerPage != null && sorts != null && filters != null) {
            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, filters, sorts).toString());
        }

        if (page != null && rowsPerPage != null && filters != null) {
            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, filters, new ArrayList<>()).toString());
        }

        if (page != null && rowsPerPage != null) {
            return ResponseEntity.ok(cropService.findAll(page, rowsPerPage, new ArrayList<>(), new ArrayList<>()).toString());
        }

        return ResponseEntity.ok(cropService.findAll().toString());
    }

    @RequestMapping(value = "/crops", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getCrops(@RequestParam("categoryId") Long categoryId) {
        PayloadModel<CropModel> payloadModel = new PayloadModel<>();

        try {
            List<Crop> crops = cropService.find(categoryId == null ? 1L : categoryId);
            if (!crops.isEmpty()) {
                List<CropModel> models = crops.stream().map(CropModel::new).collect(Collectors.toList());
                CropModel[] payload = models.toArray(new CropModel[models.size()]);
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(payload);
            } else {
                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
            }
        } catch (Exception e) {
            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
            payloadModel.setMessage(e.getLocalizedMessage());
        }

        return ResponseEntity.ok(payloadModel);
    }
}
