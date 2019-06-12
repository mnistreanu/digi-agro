package com.arobs.model.notification;


import com.arobs.entity.notification.Notification;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

public class NotificationModel implements Serializable {

    private Long id;
    private Long typeId;
    private String translationKey;
    private String message;
    private Long userId;
    private Date dateTo;
    private Duration duration;
    private long durationDays;
    private long durationHours;
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
        this.duration = entity.getDuration();
        this.durationDays = duration.toDays();
        this.durationHours = duration.toHours() - duration.toDays() *24;
        this.seenAt = entity.getSeenAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
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

    public Duration getDuration() {
        return duration;
    }

    public void setDurationDays(long durationDays) {
        this.durationDays = durationDays;
    }

    public void setDurationHours(long durationHours) {
        this.durationHours = durationHours;
    }

    public long getDurationDays() {
        return durationDays;
    }
    public long getDurationHours() {
        return durationHours;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Date getSeenAt() {
        return seenAt;
    }

    public void setSeenAt(Date seenAt) {
        this.seenAt = seenAt;
    }
}
