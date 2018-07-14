package com.arobs.controller;

import com.arobs.entity.Machine;
import com.arobs.model.MachineModel;
import com.arobs.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<MachineModel>> getModels() {

        List<Machine> items = machineService.findAll();
        List<MachineModel> models = items.stream().map(MachineModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/identifiers", method = RequestMethod.GET)
    public ResponseEntity<List<String>> fetchIdentifiers() {
        return ResponseEntity.ok(machineService.fetchIdentifiers());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MachineModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(machineService.findModelById(id));
    }

    @RequestMapping(value = "/validate-identifier", method = RequestMethod.GET)
    public ResponseEntity<Boolean> validateIdentifier(@RequestParam("id") Long id, @RequestParam("value") String value) {
        return ResponseEntity.ok(machineService.validateIdentifier(id, value));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MachineModel> save(@RequestBody MachineModel model) {
        return ResponseEntity.ok(new MachineModel(machineService.save(model)));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        machineService.remove(id);
    }

}
