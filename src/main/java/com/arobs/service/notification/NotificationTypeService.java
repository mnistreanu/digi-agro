package com.arobs.service.notification;

import com.arobs.entity.notification.NotificationType;
import com.arobs.repository.NotificationTypeRepository;
import com.arobs.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypeService extends BaseEntityService<NotificationType, NotificationTypeRepository> {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Override
    public NotificationTypeRepository getRepository() {
        return notificationTypeRepository;
    }

    public List<NotificationType> find() {
        return getRepository().find();
    }
}
