package com.arobs.controller;

import com.arobs.entity.Reminder;
import com.arobs.entity.AgroWorkType;
import com.arobs.model.agrowork.AgroWorkTypeModel;
import com.arobs.model.PayloadModel;
import com.arobs.model.reminder.ReminderModel;
import com.arobs.model.reminder.ReminderChangeModel;
import com.arobs.service.ReminderService;
import com.arobs.service.agrowork.AgroWorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;
    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getScheduledTasks() {
        Long tenantId  = 1L;
        Date scheduledTime = new Date();
        PayloadModel<ReminderModel> payloadModel = new PayloadModel<>();

        try {
            List<Reminder> reminders = reminderService.find(tenantId, scheduledTime);
            if (!reminders.isEmpty()) {
                List<ReminderModel> models = reminders.stream().map(ReminderModel::new).collect(Collectors.toList());
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(models);
            } else {
                payloadModel.setStatus(PayloadModel.STATUS_WARNING);
            }
        } catch (Exception e) {
            payloadModel.setStatus(PayloadModel.STATUS_ERROR);
            payloadModel.setMessage(e.getLocalizedMessage());
        }

        return ResponseEntity.ok(payloadModel);
    }

    @RequestMapping(value = "/work-types", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getAgroWorkTypes() {
        Long tenantId  = 1L;

        PayloadModel<AgroWorkTypeModel> payloadModel = new PayloadModel<>();

        try {
            List<AgroWorkType> workTypes = agroWorkTypeService.find(tenantId);
            if (!workTypes.isEmpty()) {
                List<AgroWorkTypeModel> models = workTypes.stream().map(AgroWorkTypeModel::new).collect(Collectors.toList());
                payloadModel.setStatus(PayloadModel.STATUS_SUCCESS);
                payloadModel.setPayload(models);
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
//    public ResponseEntity<ReminderModel> getModel(@PathVariable Long id) {
//        Reminder at = agroTaskService.findOne(id);
//        return ResponseEntity.ok(new ReminderModel(at));
//    }
//
//    @RequestMapping(value = "/validate-identifier", method = RequestMethod.GET)
//    public ResponseEntity<Boolean> validateIdentifier(@RequestParam("id") Long id, @RequestParam("value") String value) {
//        return ResponseEntity.ok(agroTaskService.validateIdentifier(id, value));
//    }
//

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public void changeSchedule(@RequestBody ReminderChangeModel model) {
        reminderService.changeSchedule(model.getId(), model.getStarting(), model.getEnding());
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ReminderModel> save(@RequestBody ReminderModel model) {

        // todo: adjust tenant
        if (model.getTenantId() == null) {
            model.setTenantId(1L);
        }

        return ResponseEntity.ok(new ReminderModel(reminderService.save(model)));
    }

    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        reminderService.remove(id);
    }

}
