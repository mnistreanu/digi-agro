package com.arobs.service.notification;

import com.arobs.entity.Notification;
import com.arobs.repository.NotificationRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService extends BaseEntityService<Notification, NotificationRepository> {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationRepository getRepository() {
        return notificationRepository;
    }

    public List<Notification> find(Long userId) {
        return getRepository().find(userId);
    }

    public List<Notification> findNotSeen(Long userId, Date dateFrom) {
        dateFrom = dateFrom == null ? new Date() : dateFrom;
        return getRepository().findNotSeen(userId, dateFrom);
    }

    @Transactional
    public void see(List<Long> ids) {
        getRepository().see(ids, new Date());
    }
}
