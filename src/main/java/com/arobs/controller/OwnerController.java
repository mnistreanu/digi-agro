package com.arobs.controller;

import com.arobs.entity.Owner;
import com.arobs.model.OwnerModel;
import com.arobs.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owner")
@Deprecated
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<OwnerModel>> getModels() {

        List<Owner> owners = ownerService.findAll();
        List<OwnerModel> models = owners.stream().map(OwnerModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OwnerModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.findModelById(id));
    }

    @RequestMapping(value = "/validate-name", method = RequestMethod.GET)
    public ResponseEntity<Boolean> validateName(@RequestParam("id") Long id, @RequestParam("name") String name) {
        return ResponseEntity.ok(ownerService.validateName(id, name));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<OwnerModel> save(@RequestBody OwnerModel model) {
        return ResponseEntity.ok(new OwnerModel(ownerService.save(model)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        ownerService.remove(id);
    }

}
