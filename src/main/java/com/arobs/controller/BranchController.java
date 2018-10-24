package com.arobs.controller;

import com.arobs.entity.Branch;
import com.arobs.model.ListItemModel;
import com.arobs.model.branch.BranchFilterModel;
import com.arobs.model.branch.BranchModel;
import com.arobs.service.TenantBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private TenantBranchService tenantBranchService;

    @RequestMapping(value = "/list-items", method = RequestMethod.POST)
    public ResponseEntity<List<ListItemModel>> findByTenants(@RequestBody BranchFilterModel filterModel) {
        List<Branch> branches = tenantBranchService.find(filterModel.getTenants(), filterModel.getSkipRootId());
        List<ListItemModel> models = branches.stream().map(b -> new ListItemModel(b.getId(), b.getName())).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<BranchModel>> getModels(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");
        List<Branch> branches = tenantBranchService.find(Collections.singletonList(tenantId));
        List<BranchModel> models = branches.stream().map(BranchModel::new).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BranchModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(new BranchModel(tenantBranchService.findOne(id)));
    }

    @RequestMapping(value = "/unique", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUnique(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam("value") String value) {
        return ResponseEntity.ok(tenantBranchService.isUnique(id, value));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BranchModel> save(HttpSession session, @RequestBody BranchModel model) {
        Long tenantId = (Long) session.getAttribute("tenant");
        model.setTenantId(tenantId);
        return ResponseEntity.ok(new BranchModel(tenantBranchService.save(model)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        tenantBranchService.remove(id);
    }

}
