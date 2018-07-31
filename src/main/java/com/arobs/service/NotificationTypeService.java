package com.arobs.service;

import com.arobs.entity.NotificationType;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypeService implements HasRepository<NotificationTypeRepository> {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationType findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<NotificationType> find() {
        return getRepository().find();
    }

    @Override
    public NotificationTypeRepository getRepository() {
        return notificationTypeRepository;
    }
}
