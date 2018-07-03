package com.arobs.model.userAccount;


import com.arobs.entity.UserAccount;

public class UserAccountLightModel {

    private Long id;

    private String username;

    private String firstName;
    private String lastName;

    private String email;

    public UserAccountLightModel() {
    }

    public UserAccountLightModel(UserAccount entity) {
        id = entity.getId();
        username = entity.getUsername();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email= entity.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
