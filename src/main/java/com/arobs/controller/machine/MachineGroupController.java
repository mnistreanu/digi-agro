package com.arobs.controller.machine;

import com.arobs.entity.MachineGroup;
import com.arobs.model.machine.MachineGroupModel;
import com.arobs.service.MachineGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machine-group")
public class MachineGroupController {

    @Autowired
    private MachineGroupService machineGroupService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<MachineGroupModel>> getModels(HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        List<MachineGroup> items = machineGroupService.find(tenant);
        List<MachineGroupModel> models = items.stream().map(MachineGroupModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MachineGroupModel> getModel(@PathVariable Long id) {
        MachineGroup machineGroup = machineGroupService.findOne(id);
        return ResponseEntity.ok(new MachineGroupModel(machineGroup));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MachineGroupModel> save(@RequestBody MachineGroupModel model, HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(new MachineGroupModel(machineGroupService.save(model, tenant)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        machineGroupService.delete(id);
    }

}
