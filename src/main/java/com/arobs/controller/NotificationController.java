package com.arobs.controller;

import com.arobs.entity.Notification;
import com.arobs.model.notification.NotificationModel;
import com.arobs.service.AuthService;
import com.arobs.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<NotificationModel>> getNotifications(
            @RequestParam(value = "all", required = false) Boolean all,
            @RequestParam(value = "dateFrom", required = false) Date dateFrom) {

        Long userId = authService.getCurrentUser().getId();
        List<Notification> notifications;

        if (all != null && all) {
            notifications = notificationService.find(userId);
        }
        else {
            notifications = notificationService.findNotSeen(userId, dateFrom);
        }

        List<NotificationModel> models = notifications.stream().map(NotificationModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/see", method = RequestMethod.POST)
    public void markAsSeenNotifications(@RequestBody List<Long> ids) {
        notificationService.see(ids);
    }

}
