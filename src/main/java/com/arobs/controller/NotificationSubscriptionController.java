package com.arobs.controller;

import com.arobs.entity.notification.NotificationSubscription;
import com.arobs.entity.notification.NotificationType;
import com.arobs.model.notification.NotificationChangeSubscriptionModel;
import com.arobs.model.notification.NotificationSubscriptionModel;
import com.arobs.model.notification.NotificationTypeModel;
import com.arobs.service.AuthService;
import com.arobs.service.notification.NotificationSubscriptionService;
import com.arobs.service.notification.NotificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notification-subscription")
public class NotificationSubscriptionController {

    @Autowired
    private AuthService authService;

    @Autowired
    private NotificationTypeService notificationTypeService;

    @Autowired
    private NotificationSubscriptionService notificationSubscriptionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<NotificationSubscriptionModel>> getNotificationSubscriptions() {

        List<NotificationType> types = notificationTypeService.find();
        Long userId = authService.getCurrentUser().getId();
        List<NotificationSubscription> subscriptions = notificationSubscriptionService.find(userId);
        Set<Long> subscribedTypes = subscriptions.stream().map(ns -> ns.getNotificationType().getId()).collect(Collectors.toSet());

        List<NotificationSubscriptionModel> models = new ArrayList<>();

        for (NotificationType type : types) {
            NotificationSubscriptionModel model = new NotificationSubscriptionModel();
            model.setTypeModel(new NotificationTypeModel(type));
            model.setSubscribed(subscribedTypes.contains(type.getId()));
            models.add(model);
        }

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void changeSubscription(@RequestBody NotificationChangeSubscriptionModel model) {
        Long userId = authService.getCurrentUser().getId();
        this.notificationSubscriptionService.changeSubscription(userId, model.getTypeId(), model.getSubscribed());
    }

}
