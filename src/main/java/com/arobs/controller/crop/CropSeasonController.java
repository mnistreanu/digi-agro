package com.arobs.controller.crop;

import com.arobs.entity.CropSeason;
import com.arobs.model.branch.BranchModel;
import com.arobs.model.crop.CropSeasonModel;
import com.arobs.service.crop.CropSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/crop-season")
public class CropSeasonController {

    @Autowired
    private CropSeasonService cropSeasonService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CropSeasonModel>> getCropSeasons(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<CropSeason> seasons = cropSeasonService.find(tenantId);
        List<CropSeasonModel> models = seasons.stream().map(CropSeasonModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CropSeasonModel> findById(@PathVariable("id") final Long id) {

        CropSeason cropSeason = cropSeasonService.findOne(id);

        if (cropSeason != null) {
            return ResponseEntity.ok(new CropSeasonModel(cropSeason));
        }

        return ResponseEntity.notFound().build();
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CropSeasonModel> save(HttpSession session, @RequestBody CropSeasonModel model) {
        Long tenantId = (Long) session.getAttribute("tenant");
        model.setTenantId(tenantId);
        return ResponseEntity.ok(new CropSeasonModel(cropSeasonService.save(model)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        cropSeasonService.delete(id);
    }

}
