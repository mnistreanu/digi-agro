package com.arobs.controller;

import com.arobs.entity.Notification;
import com.arobs.model.PayloadModel;
import com.arobs.model.notification.NotificationModel;
import com.arobs.service.NotificationService;
import com.arobs.service.NotificationSubscriptionService;
import com.arobs.service.NotificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Autowired
    private NotificationSubscriptionService subscriptionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getNotifications() {
        Long userId  = 2L;
        boolean onlyNotSeen = false;
        Date scheduledTime = new Date();
        PayloadModel<NotificationModel> payloadModel = new PayloadModel<>();

        try {
            List<Notification> notifications = notificationService.findNotSeen(userId, scheduledTime);
            if (!notifications.isEmpty()) {
                List<NotificationModel> models = notifications.stream().map(NotificationModel::new).collect(Collectors.toList());
                NotificationModel[] payload = models.toArray(new NotificationModel[models.size()]);
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


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<PayloadModel> getAllNotifications() {
        Long userId  = 2L;
        PayloadModel<NotificationModel> payloadModel = new PayloadModel<>();

        try {
            List<Notification> notifications = notificationService.findAll(userId);
            if (!notifications.isEmpty()) {
                List<NotificationModel> models = notifications.stream().map(NotificationModel::new).collect(Collectors.toList());
                NotificationModel[] payload = models.toArray(new NotificationModel[models.size()]);
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

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void see(@PathVariable Long id) {
        notificationService.see(id);
    }

}
