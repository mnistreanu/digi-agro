package com.arobs.model;

import com.arobs.entity.Employee;

public class EmployeeModel {

    private Long id;

    private String title;
    private String firstName;
    private String lastName;

    public EmployeeModel() {
    }

    public EmployeeModel(Employee entity) {
        id = entity.getId();
        title = entity.getTitle();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
