package com.arobs.controller.machine;

import com.arobs.entity.Machine;
import com.arobs.model.machine.MachineModel;
import com.arobs.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<MachineModel>> getModels(
            @RequestParam(value = "machineGroupId", required = false) Long machineGroupId,
            HttpSession session) {

        Long tenant = (Long) session.getAttribute("tenant");
        List<Machine> items = machineService.find(tenant, machineGroupId);
        List<MachineModel> models = items.stream().map(MachineModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MachineModel> getModel(@PathVariable Long id) {
        Machine machine = machineService.findOne(id);
        return ResponseEntity.ok(new MachineModel(machine));
    }

    @RequestMapping(value = "/unique", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUnique(@RequestParam(value = "id", required = false) Long id,
                                            @RequestParam("field") String field, @RequestParam("value") String value) {
        return ResponseEntity.ok(machineService.isUnique(id, field, value));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MachineModel> save(@RequestBody MachineModel model, HttpSession session) {
        Long tenant = (Long) session.getAttribute("tenant");
        return ResponseEntity.ok(new MachineModel(machineService.save(model, tenant)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        machineService.remove(id);
    }

}
