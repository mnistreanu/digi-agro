package com.arobs.model.reminder;

import java.util.Date;

public class ReminderChangeModel {

    private Long id;
    private Date starting;
    private Date ending;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStarting() {
        return starting;
    }

    public void setStarting(Date starting) {
        this.starting = starting;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }
}
