package com.arobs.model.userAccount;

import com.arobs.entity.Authority;
import com.arobs.entity.Tenant;
import com.arobs.entity.TenantBranch;
import com.arobs.entity.UserAccount;
import com.arobs.enums.AuthorityName;

import java.util.List;
import java.util.stream.Collectors;

public class UserAccountModel {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private String mobilePhone;

    private boolean active;

    private AuthorityName roleName;

    private List<Long> tenants;
    private List<Long> branches;

    private String logoUrl;

    private String language;

    public UserAccountModel() {
    }

    public UserAccountModel(UserAccount entity) {
        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        address = entity.getAddress();
        phone = entity.getPhone();
        mobilePhone = entity.getMobilePhone();
        active = entity.isActive();
        logoUrl = entity.getSafeLogoUrl();
        language = entity.getLanguage();

        for (Authority authority : entity.getAuthorities()) {
            if (authority.getName().name().startsWith("ROLE_")) {
                roleName = authority.getName();
                break;
            }
        }

        if (entity.getTenants() != null) {
            tenants = entity.getTenants().stream().map(Tenant::getId).collect(Collectors.toList());
        }

        if (entity.getBranches() != null) {
            branches = entity.getBranches().stream().map(TenantBranch::getId).collect(Collectors.toList());
        }

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

    public AuthorityName getRoleName() {
        return roleName;
    }

    public void setRoleName(AuthorityName roleName) {
        this.roleName = roleName;
    }

    public List<Long> getTenants() {
        return tenants;
    }

    public void setTenants(List<Long> tenants) {
        this.tenants = tenants;
    }

    public List<Long> getBranches() {
        return branches;
    }

    public void setBranches(List<Long> branches) {
        this.branches = branches;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
