package com.arobs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 02.09.2018.
 */
@Entity
@Table(name = "agro_work_item")
public class AgroWorkItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agro_work_id")
    private Long agroWorkId;

    @Column(name = "item_title")
    private String title;

    @Column(name = "item_value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgroWorkId() {
        return agroWorkId;
    }

    public void setAgroWorkId(Long agroWorkId) {
        this.agroWorkId = agroWorkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
