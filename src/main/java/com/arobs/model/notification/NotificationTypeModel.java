package com.arobs.model.notification;

import com.arobs.entity.NotificationType;

public class NotificationTypeModel {

    private Long id;
    private String key;

    public NotificationTypeModel(NotificationType type) {
        id = type.getId();
        key = type.getTranslationKey();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
