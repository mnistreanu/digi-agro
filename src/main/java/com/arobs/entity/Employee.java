package com.arobs.entity;


import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    private String title;
    private String firstName;
    private String lastName;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public Employee() {
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
