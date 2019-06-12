package com.arobs.model.parcel;

import com.arobs.entity.parcel.ParcelEvent;
import java.util.Date;


public class ParcelEventModel {

    private Long id;

    private Long parcelId;

    private Long eventTypeId;
    private Long eventTypeDetailId;

    private Date date;
    private String title;
    private String description;

    public ParcelEventModel() {
    }

    public ParcelEventModel(ParcelEvent entity) {
        id = entity.getId();

        parcelId = entity.getParcelId();
        eventTypeId = entity.getEventTypeId();
        eventTypeDetailId = entity.getEventTypeDetailId();

        date = entity.getDate();
        title = entity.getTitle();
        description = entity.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
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
}
