package com.arobs.entity.parcel;

import com.arobs.entity.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ParcelEvent extends BaseEntity {

    private Long parcelId;

    private Long eventTypeId;
    private Long eventTypeDetailId;

    private Date date;
    private String title;
    private String description;

    public ParcelEvent() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Long eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public Long getEventTypeDetailId() {
        return eventTypeDetailId;
    }

    public void setEventTypeDetailId(Long eventTypeDetailId) {
        this.eventTypeDetailId = eventTypeDetailId;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }
}
