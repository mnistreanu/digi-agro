package com.arobs.model.userAccount;

import com.arobs.entity.UserAccount;

import java.util.Date;

public class UserAccountModel {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String idnp;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String address;

    private String phone;

    private String mobilePhone;

    private boolean active;

    public UserAccountModel() {
    }

    public UserAccountModel(UserAccount entity) {
        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();
        idnp = entity.getIdnp();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        birthDate = entity.getBirthDate();
        address = entity.getAddress();
        phone = entity.getPhone();
        mobilePhone = entity.getMobilePhone();

        active = entity.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdnp() {
        return idnp;
    }

    public void setIdnp(String idnp) {
        this.idnp = idnp;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
