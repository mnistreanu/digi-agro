package com.arobs.model.notification;

import java.io.Serializable;

public class NotificationSubscriptionModel implements Serializable {

    private NotificationTypeModel typeModel;
    private Boolean subscribed;

    public NotificationSubscriptionModel() {
    }

    public NotificationTypeModel getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(NotificationTypeModel typeModel) {
        this.typeModel = typeModel;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }
}
