package com.arobs.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class MapEvent {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    private Date createdAt;

    private String message;

    @Column(columnDefinition="Decimal(16, 6) default '0.000000'")
    private BigDecimal latitude, longitude;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;

    public MapEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
