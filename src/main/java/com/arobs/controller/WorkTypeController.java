package com.arobs.controller;

import com.arobs.entity.AgroWorkType;
import com.arobs.entity.Reminder;
import com.arobs.model.AgroWorkTypeModel;
import com.arobs.model.PayloadModel;
import com.arobs.model.reminder.ReminderChangeModel;
import com.arobs.model.reminder.ReminderModel;
import com.arobs.service.AgroWorkTypeService;
import com.arobs.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/work-type")
public class WorkTypeController {

    @Autowired
    private AgroWorkTypeService agroWorkTypeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<AgroWorkTypeModel>> getAgroWorkTypes(HttpSession session) {
        Long tenantId = (Long) session.getAttribute("tenant");

        List<AgroWorkType> workTypes = agroWorkTypeService.find(tenantId);
        List<AgroWorkTypeModel> models = workTypes.stream().map(AgroWorkTypeModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }
}
