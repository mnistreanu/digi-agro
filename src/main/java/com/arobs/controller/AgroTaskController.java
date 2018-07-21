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
    public ResponseEntity<PayloadModel> getScheduledTasks() {

        PayloadModel<AgroTaskModel> payloadModel = new PayloadModel<>();
        System.out.println(payloadModel);

        try {
            List<AgroTask> agroTasks = agroTaskService.findFutureTasks(1L, new Date());
            if (!agroTasks.isEmpty()) {
                List<AgroTaskModel> models = agroTasks.stream().map(AgroTaskModel::new).collect(Collectors.toList());
                AgroTaskModel[] payload = models.toArray(new AgroTaskModel[models.size()]);
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
//
//    @RequestMapping(value = "/identifiers", method = RequestMethod.GET)
//    public ResponseEntity<List<String>> fetchIdentifiers() {
//        return ResponseEntity.ok(agroTaskService.fetchIdentifiers());
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<AgroTaskModel> getModel(@PathVariable Long id) {
//        AgroTask at = agroTaskService.findOne(id);
//        return ResponseEntity.ok(new AgroTaskModel(at));
//    }
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
