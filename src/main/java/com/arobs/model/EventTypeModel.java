package com.arobs.model;

import com.arobs.entity.EventType;

import java.util.List;

public class EventTypeModel {

    private Long id;

    private String name;
    private String description;

    private Long parentId;
    private List<EventTypeModel> children;


    public EventTypeModel() {
    }

    public EventTypeModel(EventType entity) {
        id = entity.getId();

        name = entity.getName();
        description = entity.getDescription();

        EventType parent = entity.getParent();
        if (parent != null) {
            parentId = parent.getId();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<EventTypeModel> getChildren() {
        return children;
    }

    public void setChildren(List<EventTypeModel> children) {
        this.children = children;
    }
}
