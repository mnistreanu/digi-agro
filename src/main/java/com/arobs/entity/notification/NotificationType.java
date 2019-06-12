package com.arobs.entity.notification;

import com.arobs.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by mihail.gorgos on 27.07.2018.
 */
@Entity
@Table(name = "notification_type")
public class NotificationType extends BaseEntity {

    @Column(name = "translation_key", columnDefinition = "varchar(256)")
    private String translationKey;

    public String getTranslationKey() {
        return translationKey;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }
}
