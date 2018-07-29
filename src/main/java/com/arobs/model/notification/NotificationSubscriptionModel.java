package com.arobs.model.notification;

import com.arobs.entity.NotificationSubscription;

import java.io.Serializable;

public class NotificationSubscriptionModel implements Serializable {

    private Long id;
    private Integer typeId;
    private Long userId;

    public NotificationSubscriptionModel() {
    }

    public NotificationSubscriptionModel(NotificationSubscription entity) {
        this.id = entity.getId();
        this.typeId = entity.getNotificationType().getId();
        this.userId = entity.getUserId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
