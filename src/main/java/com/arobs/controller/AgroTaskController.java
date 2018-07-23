package com.arobs.controller;

import com.arobs.entity.AgroTask;
import com.arobs.entity.AgroWorkType;
import com.arobs.model.AgroTaskModel;
import com.arobs.model.AgroWorkTypeModel;
import com.arobs.model.PayloadModel;
import com.arobs.service.AgroTaskService;
import com.arobs.service.AgroWorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agroTasks")
public class AgroTaskController {

    @Autowired
    private AgroTaskService agroTaskService;

    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getScheduledTasks() {
        Long tenantId  = 1L;
        Date scheduledTime = null;
        PayloadModel<AgroTaskModel> payloadModel = new PayloadModel<>();

        try {
            List<AgroTask> agroTasks = agroTaskService.find(tenantId, scheduledTime);
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

    @RequestMapping(value = "/workTypes", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getAgroWorkTypes() {
        Long tenantId  = 1L;

        PayloadModel<AgroWorkTypeModel> payloadModel = new PayloadModel<>();

        try {
            List<AgroWorkType> workTypes = agroWorkTypeService.find(tenantId);
            if (!workTypes.isEmpty()) {
                List<AgroWorkTypeModel> models = workTypes.stream().map(AgroWorkTypeModel::new).collect(Collectors.toList());
                AgroWorkTypeModel[] payload = models.toArray(new AgroWorkTypeModel[models.size()]);
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
