package com.arobs.entity;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mihail.gorgos on 27.07.2018.
 */
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;

    @Column(name = "user_id")
    private Long userId;

    @Column(name="message", columnDefinition = "varchar(1024)")
    private String message;

    @Column(name = "date_to")
    private Date dateTo;

    @Column(name = "seen_at")
    private Date seenAt;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTo() {
        return dateTo;
    }

    @Transient
    public Duration getDuration() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateTo);
            LocalDateTime localDateTo = LocalDateTime.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

            return Duration.between(LocalDateTime.now(), localDateTo);
        } catch (Exception e) {
            return Duration.ZERO;
        }
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
