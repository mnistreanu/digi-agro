package com.arobs.model.notification;

import com.arobs.entity.NotificationSubscription;

import java.io.Serializable;

public class NotificationChangeSubscriptionModel implements Serializable {

    private Long typeId;
    private Boolean subscribed;

    public NotificationChangeSubscriptionModel() {
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
