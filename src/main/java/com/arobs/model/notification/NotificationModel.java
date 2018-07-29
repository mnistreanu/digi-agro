package com.arobs.model.notification;


import com.arobs.entity.Notification;

import java.io.Serializable;
import java.util.Date;

public class NotificationModel implements Serializable {

    private Long id;
    private Integer typeId;
    private String translationKey;
    private String message;
    private Long userId;
    private Date dateTo;
    private Date seenAt;

    public NotificationModel() {
    }

    public NotificationModel(Notification entity) {
        this.id = entity.getId();
        this.typeId = entity.getNotificationType().getId();
        this.translationKey = entity.getNotificationType().getTranslationKey();
        this.message = entity.getMessage();
        this.userId = entity.getUserId();
        this.dateTo = entity.getDateTo();
        this.seenAt = entity.getSeenAt();
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

    public String getTranslationKey() {
        return translationKey;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getSeenAt() {
        return seenAt;
    }

    public void setSeenAt(Date seenAt) {
        this.seenAt = seenAt;
    }
}
