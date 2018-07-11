package com.arobs.controller;

import com.arobs.model.ListItemModel;
import com.arobs.model.tenant.TenantFilterRequestModel;
import com.arobs.model.tenant.TenantModel;
import com.arobs.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @RequestMapping(value = "/fetchListItems", method = RequestMethod.GET)
    public ResponseEntity<List<ListItemModel>> fetchItems() {
        List<ListItemModel> models = tenantService.fetchListItems();
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/findBy", method = RequestMethod.POST)
    public ResponseEntity<List<TenantModel>> getModels(@RequestBody TenantFilterRequestModel filterRequestModel) {
        List<TenantModel> models = tenantService.findByFilter(filterRequestModel);
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TenantModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(new TenantModel(tenantService.findOne(id)));
    }

    @RequestMapping(value = "/checkNameUnique", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkNameUnique(@RequestParam("id") Long id, @RequestParam("name") String name) {
        return ResponseEntity.ok(tenantService.checkNameUnique(id, name));
    }

    @RequestMapping(value = "/checkFiscalCodeUnique", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkFiscalCodeUnique(@RequestParam("id") Long id, @RequestParam("code") String code) {
        return ResponseEntity.ok(tenantService.checkFiscalCodeUnique(id, code));
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<TenantModel> save(@RequestBody TenantModel model) {
        return ResponseEntity.ok(new TenantModel(tenantService.save(model)));
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        tenantService.remove(id);
    }

}
