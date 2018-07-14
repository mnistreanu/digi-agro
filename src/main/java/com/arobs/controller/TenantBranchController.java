package com.arobs.controller;

import com.arobs.model.ListItemModel;
import com.arobs.model.tenantBranch.TenantBranchFilterRequestModel;
import com.arobs.model.tenantBranch.TenantBranchModel;
import com.arobs.service.TenantBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenant-branch")
public class TenantBranchController {

    @Autowired
    private TenantBranchService tenantBranchService;

    @RequestMapping(value = "/list-items", method = RequestMethod.GET)
    public ResponseEntity<List<ListItemModel>> getListItems(@RequestParam("tenantId") Long tenantId, @RequestParam("skipId") Optional<Long> skipIdParam) {
        Long skipId = skipIdParam.isPresent() ? skipIdParam.get() : null;
        List<ListItemModel> models = tenantBranchService.fetchItemsByTenant(tenantId, skipId);
        return ResponseEntity.ok(models);
    }


    @RequestMapping(value = "/find-by-tenants", method = RequestMethod.POST)
    public ResponseEntity<List<ListItemModel>> findByTenants(@RequestBody List<Long> tenants) {
        List<ListItemModel> models = tenantBranchService.findByTenants(tenants);
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/find-by", method = RequestMethod.POST)
    public ResponseEntity<List<TenantBranchModel>> getModels(@RequestBody TenantBranchFilterRequestModel filterRequestModel) {
        List<TenantBranchModel> models = tenantBranchService.findByFilter(filterRequestModel);
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TenantBranchModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(new TenantBranchModel(tenantBranchService.findOne(id)));
    }

    @RequestMapping(value = "/validate-name", method = RequestMethod.GET)
    public ResponseEntity<Boolean> validateName(@RequestParam("id") Long id, @RequestParam("name") String name) {
        return ResponseEntity.ok(tenantBranchService.validateName(id, name));
    }


    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<TenantBranchModel> save(@RequestBody TenantBranchModel model) {
        return ResponseEntity.ok(new TenantBranchModel(tenantBranchService.save(model)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        tenantBranchService.remove(id);
    }

}
