package com.arobs.service.notification;

import com.arobs.entity.Notification;
import com.arobs.interfaces.HasRepository;
import com.arobs.repository.NotificationRepository;
import com.arobs.service.AuthService;
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

    public List<Notification> findAll(Long userId) {
        return getRepository().findAll(userId);
    }

    public List<Notification> findNotSeen(Long userId, Date dateFrom) {
        dateFrom = dateFrom == null ? new Date() : dateFrom;
        return getRepository().findNotSeen(userId, dateFrom);
    }

    public Notification findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional
    public void see(List<Long> ids) {
        getRepository().see(ids, new Date());
    }
}
