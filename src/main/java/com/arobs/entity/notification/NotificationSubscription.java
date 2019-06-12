package com.arobs.entity.notification;

import com.arobs.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by mihail.gorgos on 27.07.2018.
 */
@Entity
@Table(name = "notification_subscription")
public class NotificationSubscription extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;

    @Column(name = "user_id")
    private Long userId;

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
