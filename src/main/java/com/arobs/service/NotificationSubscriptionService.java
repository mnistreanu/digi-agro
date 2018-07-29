package com.arobs.service;

import com.arobs.entity.NotificationSubscription;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.NotificationSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationSubscriptionService implements HasRepository<NotificationSubscriptionRepository> {

    @Autowired
    private NotificationSubscriptionRepository notificationSubscriptionRepository;

    public NotificationSubscription findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<NotificationSubscription> find(Long userId) {
        return getRepository().find(userId);
    }

    @Override
    public NotificationSubscriptionRepository getRepository() {
        return notificationSubscriptionRepository;
    }
}
