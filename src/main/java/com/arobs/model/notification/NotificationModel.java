package com.arobs.model.notification;


import com.arobs.entity.AgroTask;
import com.arobs.entity.Notification;

import java.io.Serializable;
import java.util.Date;

public class NotificationModel implements Serializable {

    private Long id;
    private Integer notificationTypeId;
    private String message;
    private Long userId;
    private Date createdAt;
    private Date seenAt;

    public NotificationModel() {
    }

    public NotificationModel(Notification entity) {
        this.id = entity.getId();
        this.notificationTypeId = entity.getNotificationTypeId();
        this.message = entity.getMessage();
        this.userId = entity.getUserId();
        this.createdAt = entity.getCreatedAt();
        this.seenAt = entity.getSeenAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(Integer notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getSeenAt() {
        return seenAt;
    }

    public void setSeenAt(Date seenAt) {
        this.seenAt = seenAt;
    }
}
