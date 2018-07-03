package com.arobs.service;


import com.arobs.entity.UserAccount;
import com.arobs.enums.AuthorityName;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.userAccount.UserAccountModel;
import com.arobs.repository.UserAccountRepository;
import com.arobs.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserAccountService implements HasRepository<UserAccountRepository> {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private AuthorityService authorityService;

    public boolean checkUsernameUnique(Long id, String username) {
        if (id == -1) {
            return getRepository().countByUsername(username) == 0;
        }
        return getRepository().countByUsernameEscapeId(id, username) == 0;
    }

    public UserAccount findOne(Long id) {
        return getRepository().findOne(id);
    }

    public UserAccount findByUsername(String username) {
        return getRepository().findByUsername(username);
    }

    public UserAccountModel findModelById(Long id) {
        return new UserAccountModel(getRepository().findOne(id));
    }

    public List<UserAccount> findUsers() {
        return getRepository().findUsers();
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public UserAccount save(UserAccountModel model) {
        UserAccount userAccount;

        if (model.getId() == null) {
            userAccount = new UserAccount();
            userAccount.setAuthorities(authorityService.findAllByName(AuthorityName.ROLE_USER));
        }
        else {
            userAccount = findOne(model.getId());
        }

        copyValues(userAccount, model);
        return getRepository().save(userAccount);
    }

    private void copyValues(UserAccount userAccount, UserAccountModel model) {
        userAccount.setUsername(model.getUsername());
        userAccount.setEmail(model.getEmail());
        userAccount.setIdnp(model.getIdnp());
        userAccount.setFirstName(model.getFirstName());
        userAccount.setLastName(model.getLastName());
        userAccount.setBirthDate(model.getBirthDate());
        userAccount.setAddress(model.getAddress());
        userAccount.setPhone(model.getPhone());
        userAccount.setMobilePhone(model.getMobilePhone());

        if (model.getPassword() != null) {
            userAccount.setPassword(SecurityUtil.encryptPassword(model.getPassword()));
        }
    }

    @Override
    public UserAccountRepository getRepository() {
        return userAccountRepository;
    }
}
