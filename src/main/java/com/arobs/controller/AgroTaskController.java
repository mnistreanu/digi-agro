package com.arobs.controller;

import com.arobs.entity.AgroTask;
import com.arobs.model.AgroTaskModel;
import com.arobs.model.PayloadModel;
import com.arobs.service.AgroTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agroTasks")
public class AgroTaskController {

    @Autowired
    private AgroTaskService agroTaskService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<AgroTaskModel>> getModels(@RequestParam("tenant_id") Long tenantId,
                                                         @RequestParam("scheduled_date") Date scheduledDate) {

        List<AgroTask> items = agroTaskService.findFutureTasks(tenantId, scheduledDate == null ? new Date() : scheduledDate);
        List<AgroTaskModel> models = items.stream().map(AgroTaskModel::new).collect(Collectors.toList());

        PayloadModel<AgroTaskModel> model = new PayloadModel<>();
        model.setStatus(PayloadModel.STATUS_SUCCESS);

        return ResponseEntity.ok(models);
    }
//
//    @RequestMapping(value = "/identifiers", method = RequestMethod.GET)
//    public ResponseEntity<List<String>> fetchIdentifiers() {
//        return ResponseEntity.ok(agroTaskService.fetchIdentifiers());
//    }
//
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AgroTaskModel> getModel(@PathVariable Long id) {
        AgroTask at = agroTaskService.findOne(id);
        return ResponseEntity.ok(new AgroTaskModel(at));
    }
//
//    @RequestMapping(value = "/validate-identifier", method = RequestMethod.GET)
//    public ResponseEntity<Boolean> validateIdentifier(@RequestParam("id") Long id, @RequestParam("value") String value) {
//        return ResponseEntity.ok(agroTaskService.validateIdentifier(id, value));
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseEntity<AgroTaskModel> save(@RequestBody AgroTaskModel model) {
//        return ResponseEntity.ok(new AgroTaskModel(agroTaskService.save(model)));
//    }
//
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void remove(@PathVariable Long id) {
//        agroTaskService.remove(id);
//    }

}
