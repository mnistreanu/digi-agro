package com.arobs.service.notification;

import com.arobs.entity.NotificationSubscription;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.NotificationSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationSubscriptionService implements HasRepository<NotificationSubscriptionRepository> {

    @Autowired
    private NotificationSubscriptionRepository notificationSubscriptionRepository;
    @Autowired
    private NotificationTypeService notificationTypeService;

    @Override
    public NotificationSubscriptionRepository getRepository() {
        return notificationSubscriptionRepository;
    }

    public NotificationSubscription findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<NotificationSubscription> find(Long userId) {
        return getRepository().find(userId);
    }

    @Transactional
    public void changeSubscription(Long userId, Long typeId, Boolean subscribed) {
        if (subscribed) {
            NotificationSubscription notificationSubscription = new NotificationSubscription();
            notificationSubscription.setUserId(userId);
            notificationSubscription.setNotificationType(notificationTypeService.findOne(typeId));
            save(notificationSubscription);
        }
        else {
            getRepository().delete(userId, typeId);
        }
    }

    @Transactional
    private NotificationSubscription save(NotificationSubscription entity) {
        return getRepository().save(entity);
    }
}
