package com.arobs.service;

import com.arobs.entity.Notification;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService implements HasRepository<NotificationRepository> {

    @Autowired
    private AuthService authService;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationRepository getRepository() {
        return notificationRepository;
    }

    public List<Notification> find(Long userId, boolean onlyNotSeen) {
        if (onlyNotSeen) {
            return getRepository().findNotSeen(userId);
        } else {
            return getRepository().findAll(userId);
        }
    }

    public Notification findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void see(Long id) {
        getRepository().see(id);
    }
}
